package indi.wyx0k.story.core.security.crypto;


import indi.wyx0k.story.core.security.crypto.exception.CryptoMethodException;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Component
public class RSAUtil implements BaseCryptoUtil{
    private final int TRANSSIZE = 128;
    public String decrypt(String key,String msg){
        if (msg.startsWith("{")){
            return msg;
        }
        byte[] bytes = doit(false,key,msg);
        return new String(bytes);
    }
    public String encrypt(String key,String msg){
        byte[] bytes = doit(true,key,msg);
        return Base64Utils.encodeToString(bytes);
    }
    private byte[] doit(boolean isEncript,String key,String msg){
        int length = TRANSSIZE;
        String result = "";
        Cipher cipher = null;
        byte[] datas = {};
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException |NoSuchPaddingException e) {
            e.printStackTrace();
        }
        if(isEncript){
            length = length - 11;
            RSAPublicKey publicKey = getPublicKey(key);
            datas = msg.getBytes();
            try {
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }else {
            RSAPrivateKey privateKey = getPrivateKey(key);
            datas = Base64Utils.decodeFromString(msg);
            try {
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > length) {
                    buff = cipher.doFinal(datas, offSet, length);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * length;
            }
        }catch (Exception e){
            throw new CryptoMethodException("加解密阀值为["+length+"]的数据时发生异常", e);
        }
        return out.toByteArray();
    }
    private RSAPrivateKey getPrivateKey(String key){
        byte[] keyBytes = Base64Utils.decodeFromString(key.trim());
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        RSAPrivateKey privateKey = null;
        try {
            privateKey = (RSAPrivateKey)KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);
        } catch (InvalidKeySpecException|NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return privateKey;
    }
    private RSAPublicKey getPublicKey(String key){
        byte[] keyBytes = Base64Utils.decodeFromString(key.trim());
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        RSAPublicKey publicKey = null;
        try {
            publicKey = (RSAPublicKey)KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);
        } catch (InvalidKeySpecException|NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

}
