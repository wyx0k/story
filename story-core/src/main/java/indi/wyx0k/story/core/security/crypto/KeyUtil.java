package indi.wyx0k.story.core.security.crypto;

import org.springframework.stereotype.Component;

import java.security.*;
import java.util.Map;

/**
 * mynetio
 *
 * @author wyx0k
 * Created on 2019/6/4 9:41
 */
@Component
public class KeyUtil {
    public void genRSAKey(Map map){
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

    }
    public static KeyUtil getInstance() {
        return SingleKeyUtil.keyUtil;
    }
    private static class SingleKeyUtil{
        private static KeyUtil keyUtil = new KeyUtil();
    }
}
