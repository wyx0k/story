package indi.wyx0k.story.core.security.crypto.exception;

/**
 * netio
 *
 * @author wyx0k
 * Created on 2019/6/6 12:40
 */
public class CryptoMethodException extends RuntimeException {
    public CryptoMethodException() {
    }

    public CryptoMethodException(String message) {
        super(message);
    }
    public CryptoMethodException(String message,Exception e) {
        super(message);
        e.printStackTrace();
    }
}
