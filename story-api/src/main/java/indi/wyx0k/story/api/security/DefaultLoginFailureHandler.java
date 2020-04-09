package indi.wyx0k.story.api.security;

import com.alibaba.fastjson.JSON;
import indi.wyx0k.story.core.util.ResponseUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/3/17
 */
@Component
public class DefaultLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String errStr;
        if(e instanceof UsernameNotFoundException || e instanceof BadCredentialsException){
            errStr = "用户名或密码错误";
        }else{
            errStr = "您的账户存在问题或被冻结,请与管理员联系";
        }
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(ResponseUtil.failure("","登录失败["+errStr+"]")));
    }
}
