package indi.wyx0k.story.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import indi.wyx0k.story.core.entity.Role;
import indi.wyx0k.story.core.entity.RolePermission;
import indi.wyx0k.story.core.entity.User;
import indi.wyx0k.story.core.entity.UserRole;
import indi.wyx0k.story.core.mapper.RoleMapper;
import indi.wyx0k.story.core.mapper.RolePermissionMapper;
import indi.wyx0k.story.core.mapper.UserRoleMapper;
import indi.wyx0k.story.core.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author wyx0k
 * @since 2020-03-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<Role> listRolesByUserId(String userid) {
        List<String> roleIds = listRolesIdByUserId(userid);
        List<Role> result = new ArrayList<>();
        result = roleMapper.selectBatchIds(roleIds);
        return result;
    }

    @Override
    public List<String> listRolesIdByUserId(String userid) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userid);
        List<String> roleIdList = userRoleMapper.listRoleId(userid);
        return roleIdList;
    }
}
