package indi.wyx0k.story.service.user.mapper;

import indi.wyx0k.story.service.user.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wyx0k
 * @since 2020-03-30
 */

public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    List<Integer> listRoleIds(int permissionId);
    List<Integer> listPermissionIds(int roleId);
}
