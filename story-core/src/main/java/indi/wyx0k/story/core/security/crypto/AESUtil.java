package indi.wyx0k.story.core.security.crypto;

import org.springframework.stereotype.Component;

@Component
public class AESUtil implements BaseCryptoUtil{
    public String decrypt(String key,String msg){

        System.out.println(key);
        System.out.println("---");
        System.out.println(msg);
        return msg;
    }
    public String encrypt(String key,String msg){
        System.out.println(key);
        System.out.println("---");
        System.out.println(msg);
        return msg;
    }
}
