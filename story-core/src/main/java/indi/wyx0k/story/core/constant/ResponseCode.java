package indi.wyx0k.story.core.constant;

/**
 * story
 * --
 *
 * @author wyx
 * --响应代码
 * 2020/3/17
 */
public enum ResponseCode {
    SUCCESS("00","成功"),
    FAILURE("99","失败");
    ResponseCode(String code,String msg){
        this.code = code;
        this.msg= msg;
    }
    private String code;
    private String msg;
    public String code(){
        return this.code;
    }
    public String msg(){
        return this.msg;
    }
}
