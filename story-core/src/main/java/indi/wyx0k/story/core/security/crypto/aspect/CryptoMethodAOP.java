package indi.wyx0k.story.core.security.crypto.aspect;


import com.alibaba.fastjson.JSON;
import indi.wyx0k.story.core.security.crypto.BaseCryptoUtil;
import indi.wyx0k.story.core.security.crypto.BaseCryptoWrap;
import indi.wyx0k.story.core.security.crypto.annotation.CryptoMethod;
import indi.wyx0k.story.core.security.crypto.enumeration.CryptoType;
import indi.wyx0k.story.core.security.crypto.exception.CryptoMethodException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * netio
 *
 * @author wyx0k
 * Created on 2019/6/4 11:41
 */
@Aspect
@Component
public class CryptoMethodAOP {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Map<String,String> cryptoKeysMap;
    @Autowired
    private Map<String, BaseCryptoUtil> cryptoUtilMap;
    @Pointcut(value = "@annotation(indi.wyx0k.mynetio.security.crypto.annotation.CryptoMethod)")
    public void point(){

    }
    @Around("point()")
    public BaseCryptoWrap returnV(ProceedingJoinPoint joinPoint){
        logger.info("-------------------> crypto begin <--------------------");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class returnClass = method.getReturnType();
        Object[] args = joinPoint.getArgs();
        try {
            Assert.isTrue(returnClass == Object.class, "被@CryptoMethod注解的方法 返回值 必须是Object类型的");
            Assert.isInstanceOf(BaseCryptoWrap.class, args[0], "被@CryptoMethod注解的方法 参数 必须是BaseCryptoWrap类型的");
        } catch (Exception e){
            logger.error("加密的controller方法签名错误: {}",e.getMessage());
            return  new BaseCryptoWrap();
        }
        BaseCryptoWrap baseCryptoWrap = (BaseCryptoWrap) args[0];
        logger.info("接收报文成功,报文原信息为: {}",baseCryptoWrap);
        String md5 = baseCryptoWrap.getMd5();
        String body = baseCryptoWrap.getBody();
        CryptoMethod cryptoMethod = AnnotationUtils.findAnnotation(method, CryptoMethod.class);
        baseCryptoWrap = decrypto(cryptoMethod,baseCryptoWrap);
        logger.info("解密后的报文为: {}",baseCryptoWrap);
        logger.info("--------> controller begin");
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error("处理请求(controller层)时发生错误:{}",throwable.getMessage());
        }
        logger.info("--------> controller end");
        baseCryptoWrap = new BaseCryptoWrap();
        baseCryptoWrap.setBody(JSON.toJSONString(obj));
        baseCryptoWrap = encrypto(cryptoMethod,baseCryptoWrap);
        logger.info("加密后的报文为: {}",baseCryptoWrap);
        logger.info("-------------------> crypto end <--------------------");
        return baseCryptoWrap;
    }

    private BaseCryptoWrap decrypto(CryptoMethod cryptoMethod, BaseCryptoWrap baseCryptoWrap){
        CryptoType cryptoType = cryptoMethod.value();
        String key = getKey(cryptoMethod,false);
        BaseCryptoUtil baseCryptoUtil = cryptoUtilMap.get(cryptoMethod.value().getName()+"Util");
        String result = baseCryptoUtil.decrypt(key,baseCryptoWrap.getBody());
        baseCryptoWrap.setBody(result);
        return baseCryptoWrap;
    }
    private BaseCryptoWrap encrypto(CryptoMethod cryptoMethod, BaseCryptoWrap baseCryptoWrap){
        String key = getKey(cryptoMethod,true);
        BaseCryptoUtil baseCryptoUtil = cryptoUtilMap.get(cryptoMethod.value().getName()+"Util");
        String result = baseCryptoUtil.encrypt(key,baseCryptoWrap.getBody());
        baseCryptoWrap.setBody(result);
        return baseCryptoWrap;
    }

    private String getKey(CryptoMethod cryptoMethod,boolean encryptIs){
        CryptoType cryptoType = cryptoMethod.value();
        if(cryptoType == CryptoType.CONNECT){
            return getConnectKey(cryptoMethod);
        }
        boolean pairIs = cryptoType.getPairIs();
        String name = cryptoType.getName();
        String key = "";
        String keyPath = cryptoMethod.keyPath();
        String[] keyNames = cryptoMethod.keyNames();
        if(pairIs){
            //是一对密钥
            if(null == keyPath || "".equals(keyPath)){
                key = cryptoKeysMap.get(name+"_"+(encryptIs?"PUB":"PRI"));
            }else{
                if(keyNames.length==0||"".equals(cryptoMethod.decryptKey())||"".equals(cryptoMethod.encryptKey())){
                    logger.error("非对称秘钥加密或解密时遇到错误(必须指定加密和解密秘钥)：如果@CryptoMethod注解的keyPath不为空时，所有的其他字段也不能为空");
                    throw new CryptoMethodException("加密或解密时遇到错误：如果@CryptoMethod注解的keyPath不为空时，所有的其他字段也不能为空");
                }
                String tmpKeyName = (encryptIs?cryptoMethod.encryptKey():cryptoMethod.decryptKey());
                key = getKeyFromFile(keyPath,tmpKeyName+".key");
            }
        }else{
            if(null == keyPath || "".equals(keyPath)){
                key = cryptoKeysMap.get(name);
            }else{
                key = getKeyFromFile(keyPath,keyNames[0]+".key");
            }
        }
        return key;
    }
    private String getConnectKey(CryptoMethod cryptoMethod){
        return null;
    }
    private String getKeyFromFile(String path,String name){
        if("/".equals(path)){
            path = "";
        }
        String key = cryptoKeysMap.get("FILE_"+name);
        if(key != null&&key.length() > 0){
            logger.info("从缓存中获取key成功");
            return key;
        }
        if(path.endsWith(File.separator)){
            path = path.substring(0,path.length()-1);
        }
        ClassPathResource classPathResource = new ClassPathResource(path+File.separator+name);
        StringBuilder sb = new StringBuilder();
        try(FileInputStream fileInputStream = new FileInputStream(classPathResource.getFile());) {
            long startTime = System.currentTimeMillis();
            byte[] buf = new byte[1024];
            while ((fileInputStream.read(buf)) != -1) {
                sb.append(new String(buf, "utf-8"));
                buf = new byte[1024];
            }
            logger.info("获取key成功 用时:{}毫秒" , System.currentTimeMillis()-startTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        key = sb.toString();
        cryptoKeysMap.put("FILE_"+name,key);
        return key;
    }
}
