package indi.wyx0k.story.api.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import indi.wyx0k.story.service.user.entity.Role;
import indi.wyx0k.story.service.user.entity.User;
import indi.wyx0k.story.service.user.service.IRoleService;
import indi.wyx0k.story.service.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/3/30
 */
@Slf4j
@Component
public class StoryUserDetailService implements UserDetailsService {
    @Autowired
    IUserService userService;
    @Autowired
    IRoleService roleService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        查询用户
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",s);
        User user = userService.getOne(queryWrapper);
        if(null == user){
            throw new UsernameNotFoundException("账号或密码不正确");
        }
        if(true == user.getFreeze()){
            throw new UsernameNotFoundException("账号被冻结~");
        }

//        查询角色
        List<Role> roles= roleService.listRolesByUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : roles){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            grantedAuthorities.add(simpleGrantedAuthority);
        }

        return new org.springframework.security.core.userdetails.User(s,user.getPassword(),grantedAuthorities);
    }
}
