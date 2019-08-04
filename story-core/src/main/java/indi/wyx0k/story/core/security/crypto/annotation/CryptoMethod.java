package indi.wyx0k.story.core.security.crypto.annotation;

import indi.wyx0k.story.core.security.crypto.enumeration.CryptoType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * netio
 *
 * @author wyx0k
 * Created on 2019/6/4 11:50
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CryptoMethod {
    CryptoType value() default CryptoType.AES;
    String keyPath() default "";
    String[] keyNames() default {};
    String decryptKey() default "";
    String encryptKey() default "";
}
