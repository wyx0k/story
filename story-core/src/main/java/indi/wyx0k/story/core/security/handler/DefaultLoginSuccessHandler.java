package indi.wyx0k.story.core.security.handler;

import com.alibaba.fastjson.JSON;
import indi.wyx0k.story.core.util.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
public class DefaultLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //记录登录信息-用户名-时间-ip
        //返回登录成功

        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(ResponseUtil.success("","登录成功")));
    }
}
