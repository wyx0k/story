package indi.wyx0k.story.core.security.crypto;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
public class MD5Util implements BaseCryptoUtil {
    public String encrypt(String msg) {
        return encrypt(null,msg);
    }
    @Override public String encrypt(String key, String msg) {
        String encrypt = "";
        try {
            encrypt = DigestUtils.md5DigestAsHex(new ByteArrayInputStream(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encrypt;
    }

    public  String decrypt(String key,String msg){
        return msg;
    }
}
