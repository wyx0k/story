package indi.wyx0k.story.core.security.crypto.config;

import indi.wyx0k.story.core.security.crypto.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

/**
 * netio
 *
 * @author wyx0k
 * Created on 2019/6/6 15:46
 */
@Component
public class RefreshKeysScheduler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Map<String,String> cryptoKeysMap;
    @Scheduled(cron = "${crypto.refresh}")
    public void refreshKeys(){
        logger.info("------------------> 更新秘钥开始 <-------------------");
        updateRSA();

        logger.info("------------------> 更新秘钥完成 <-------------------");
    }
    private void updateRSA(){
        KeyPair keyPair = Key.getInstance().getRSAKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        cryptoKeysMap.put("RSA_PUB",Base64Utils.encodeToString(publicKey.getEncoded()));
        cryptoKeysMap.put("RSA_PRI",Base64Utils.encodeToString(privateKey.getEncoded()));
        logger.info("RSA秘钥对更新完毕");
    }
}
