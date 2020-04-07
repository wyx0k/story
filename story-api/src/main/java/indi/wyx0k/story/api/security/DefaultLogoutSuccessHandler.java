package indi.wyx0k.story.api.security;

import com.alibaba.fastjson.JSON;
import indi.wyx0k.story.core.util.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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
public class DefaultLogoutSuccessHandler implements LogoutSuccessHandler  {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(ResponseUtil.success("","退出成功")));
    }
}
