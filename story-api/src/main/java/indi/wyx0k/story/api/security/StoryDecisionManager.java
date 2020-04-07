package indi.wyx0k.story.api.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/6
 */
@Slf4j
@Component
public class StoryDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for(GrantedAuthority authority : authorities){
            if(configAttributes.stream().anyMatch((ConfigAttribute configAttribute)-> {
                if(null == configAttribute || null == configAttribute.getAttribute()){
                    throw new AccessDeniedException("哇哦,没有这个地址哦~");
                }
                if(configAttribute.getAttribute().equals("NOSUCH_PERMISSION")){
                    throw new AccessDeniedException("哇哦,没有这个地址哦~");
                }
                if(configAttribute.getAttribute().equals(authority.getAuthority())){
                    return true;
                }
                return false;
            })){
                return;
            }
        }
        throw new AccessDeniedException("哇哦,您没有权限访问该资源诶~");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
