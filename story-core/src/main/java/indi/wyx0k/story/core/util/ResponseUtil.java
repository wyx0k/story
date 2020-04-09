package indi.wyx0k.story.core.util;

import com.alibaba.fastjson.JSON;
import indi.wyx0k.story.core.common.BaseResponse;
import indi.wyx0k.story.core.constant.ResponseCode;
import lombok.extern.slf4j.Slf4j;

/**
 * story
 * --
 *
 * @author wyx
 * --返回对象构造工具类
 * 2020/3/17
 */
@Slf4j
public class ResponseUtil {
    private ResponseUtil(){}
    public static BaseResponse success(String token,Object msg){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ResponseCode.SUCCESS.code());
        baseResponse.setToken(token);
        baseResponse.setMsg(msg);
        return baseResponse;
    }
    public static BaseResponse success(String token,String msg){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ResponseCode.SUCCESS.code());
        baseResponse.setToken(token);
        baseResponse.setMsg(msg);
        return baseResponse;
    }
    public static BaseResponse failure(String token,String msg){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ResponseCode.FAILURE.code());
        baseResponse.setToken(token);
        baseResponse.setMsg(msg);
        return baseResponse;
    }
    public static BaseResponse failure(String token,ResponseCode code){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code.code());
        baseResponse.setToken(token);
        baseResponse.setMsg(code.msg());
        return baseResponse;
    }
    public static BaseResponse response(String token,ResponseCode code,String msg){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code.code());
        baseResponse.setToken(token);
        baseResponse.setMsg(msg);
        return baseResponse;
    }

}
