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
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Override
    public List<Role> listRolesByUserId(int userid) {
        List<Integer> roleIds = listRolesIdByUserId(userid);
        List<Role> result = new ArrayList<>();
        result = roleMapper.selectBatchIds(roleIds);
        return result;
    }

    @Override
    public List<Integer> listRolesIdByUserId(int userid) {
        List<Integer> roleIdList = userRoleMapper.listRoleId(userid);
        return roleIdList;
    }

    @Override
    public List<Role> listRolesByPermissionId(int permissionId) {
        List<Integer> roleIds = listRolesIdByPermissionId(permissionId);
        List<Role> result = new ArrayList<>();
        result = roleMapper.selectBatchIds(roleIds);
        return result;
    }

    @Override
    public List<Integer> listRolesIdByPermissionId(int permissionId) {
        List<Integer> roleIdList = rolePermissionMapper.listRoleIds(permissionId);
        return roleIdList;
    }
}
