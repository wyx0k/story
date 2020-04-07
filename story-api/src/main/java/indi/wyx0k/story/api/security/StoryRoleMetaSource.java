package indi.wyx0k.story.api.security;


import indi.wyx0k.story.service.user.entity.Permission;
import indi.wyx0k.story.service.user.entity.Role;
import indi.wyx0k.story.service.user.service.IPermissionService;
import indi.wyx0k.story.service.user.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * story
 * --
 * 生成授权规则:根据路径返回允许的角色
 * @author wyx
 * --
 * 2020/4/6
 */
@Slf4j
@Component
public class StoryRoleMetaSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IRoleService roleService;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Permission> permissions = permissionService.list();
        for(Permission permission : permissions){
            if(antPathMatcher.match(permission.getPath(),requestUrl)){
                List<Role> roles = roleService.listRolesByPermissionId(permission.getId());
                String[] roleNames = roles.stream().map(role -> role.getRoleName()).toArray(size ->new String[size]);
                return SecurityConfig.createList(roleNames);
            }
        }

        return SecurityConfig.createList("NOSUCH_PERMISSION");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
