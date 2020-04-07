package indi.wyx0k.story.api.security;

import com.alibaba.fastjson.JSON;
import indi.wyx0k.story.core.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/3/17
 */
@Slf4j
@Component
public class DefaultLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //记录登录信息-用户名-时间-ip
        //返回登录成功
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user = (User) authentication.getPrincipal();

        log.info("用户: [{}] 在 [{}] 登录了系统",user.getUsername(),simpleDateFormat.format(new Date()));
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(ResponseUtil.success("","登录成功")));
    }
}
