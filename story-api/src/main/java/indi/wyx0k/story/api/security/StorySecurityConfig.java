package indi.wyx0k.story.api.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/3/13
 */
@Configuration
public class StorySecurityConfig extends WebSecurityConfigurerAdapter {
//    常规配置
//    -----------------------------------------------
    @Value("${story.security.url.login:/login}")
    private String loginUrl;
    @Value("${story.security.url.login:/logout}")
    private String logoutUrl;
//    认证
//    -----------------------------------------------
    @Autowired
    private StoryUserDetailService storyUserDetailService;
    @Autowired
    private DefaultLoginSuccessHandler defaultLoginSuccessHandler;
    @Autowired
    private DefaultLoginFailureHandler defaultLoginFailureHandler;
    @Autowired
    private DefaultLogoutSuccessHandler defaultLogoutSuccessHandler;

//    授权
//    ----------------------------------------------------
    @Autowired
    private StoryDecisionManager storyDecisionManager;
    @Autowired
    private StoryRoleMetaSource storyRoleMetaSource;
    @Autowired
    private DefaultAccessDeniedHandler defaultAccessDeniedHandler;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        //指定允许跨域的请求(*所有)：http://wap.ivt.guansichou.com
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "X-User-Agent", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setAccessDecisionManager(storyDecisionManager);
                o.setSecurityMetadataSource(storyRoleMetaSource);
                return o;
            }
        })
                .and().formLogin()
                    .loginProcessingUrl(loginUrl)
                    .successHandler(defaultLoginSuccessHandler)
                    .failureHandler(defaultLoginFailureHandler)
                .and().logout()
                    .logoutUrl(logoutUrl)
                    .logoutSuccessHandler(defaultLogoutSuccessHandler).permitAll()
                .and().exceptionHandling().accessDeniedHandler(defaultAccessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(storyUserDetailService);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
