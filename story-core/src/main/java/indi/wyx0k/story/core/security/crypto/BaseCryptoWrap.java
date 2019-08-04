package indi.wyx0k.story.core.security.crypto;

/**
 * netio
 *
 * @author wyx0k
 * Created on 2019/6/4 15:57
 */
public class BaseCryptoWrap {
    String md5;
    String body;
    public BaseCryptoWrap(){}
    public BaseCryptoWrap(String md5, String body) {
        this.md5 = md5;
        this.body = body;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override public String toString() {
        return "BaseRequest{" +
                "md5='" + md5 + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
