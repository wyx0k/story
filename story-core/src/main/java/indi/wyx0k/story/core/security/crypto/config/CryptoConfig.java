package indi.wyx0k.story.core.security.crypto.config;

import indi.wyx0k.story.core.security.crypto.BaseCryptoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;

import java.security.*;
import java.util.HashMap;
import java.util.Map;

/**
 * mynetio
 *
 * @author zkr-wyx0k
 * Created on 2019/5/28 9:36
 */
@Configuration
public class CryptoConfig implements ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ApplicationContext applicationContext;
    @Override public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Bean
    public Map<String, BaseCryptoUtil> cryptoUtilMap(){
        Map<String,BaseCryptoUtil> map = new HashMap<>();
//        spring会自动查找所有实现该接口的Bean并自动放进去
        return map;
    }
    @Bean
    public Map<String,String> cryptoKeysMap(){
        Map<String,String> map = new HashMap<>();
        initKeys(map);
        return map;
    }
    private void initKeys(Map<String,String> map){
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            logger.error("没有这样的算法：{}",e);
        }
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        map.put("RSA_PUB",Base64Utils.encodeToString(publicKey.getEncoded()));
        map.put("RSA_PRI",Base64Utils.encodeToString(privateKey.getEncoded()));
        logger.info("RSA 密钥初始化完毕！");
    }

}
