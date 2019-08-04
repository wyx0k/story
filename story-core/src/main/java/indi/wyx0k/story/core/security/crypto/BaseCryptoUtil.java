package indi.wyx0k.story.core.security.crypto;

/**
 * mynetio
 *
 * @author zkr-wyx0k
 * Created on 2019/5/28 9:39
 */
public interface BaseCryptoUtil {
    String decrypt(String key, String msg);
    String encrypt(String key, String msg);
}
