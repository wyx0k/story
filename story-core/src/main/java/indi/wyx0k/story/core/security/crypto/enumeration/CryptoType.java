package indi.wyx0k.story.core.security.crypto.enumeration;

/**
 * story
 *
 * @Author wyx0k
 * 2019/8/4
 **/
public enum CryptoType {
    RSA(1001,"RSA",true),AES(1002,"AES",false),NONE(1003,"NONE",false),MD5(1004,"MD5",false),CONNECT(1005,"CONNECT",true);
    private int code;
    private String name;
    private boolean pairIs;
    CryptoType(int code,String name,boolean pairIs) {
        this.code = code;
        this.name = name;
        this.pairIs = pairIs;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean getPairIs() {
        return pairIs;
    }
}
