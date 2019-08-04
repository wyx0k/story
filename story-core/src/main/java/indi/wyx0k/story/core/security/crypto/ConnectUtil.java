package indi.wyx0k.story.core.security.crypto;

import org.springframework.stereotype.Component;

/**
 * 初次连接时的加密方式
 */
@Component("CONNECTUtil")
public class ConnectUtil implements BaseCryptoUtil{
    @Override public String decrypt(String key, String msg) {
        return null;
    }

    @Override public String encrypt(String key, String msg) {
        return null;
    }
}
