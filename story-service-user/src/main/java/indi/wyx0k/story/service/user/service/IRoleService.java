package indi.wyx0k.story.service.user.service;

import indi.wyx0k.story.service.user.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author wyx0k
 * @since 2020-03-18
 */
public interface IRoleService extends IService<Role> {
    List<Role> listRolesByUserId(int userid);
    List<Integer> listRolesIdByUserId(int userid);
    List<Role> listRolesByPermissionId(int permissionId);
    List<Integer> listRolesIdByPermissionId(int permissionId);
}
