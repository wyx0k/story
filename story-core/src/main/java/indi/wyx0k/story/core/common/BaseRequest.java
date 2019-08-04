package indi.wyx0k.story.core.common;

/**
 * story
 *
 * @author wyx0k
 * 2019/8/4
 **/
public class BaseRequest {
    String md5;
    String body;
    public BaseRequest(){}
    public BaseRequest(String md5, String body) {
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
