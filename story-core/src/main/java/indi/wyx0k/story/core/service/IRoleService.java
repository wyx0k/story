package indi.wyx0k.story.core.service;

import indi.wyx0k.story.core.entity.Role;
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
    List<Role> listRolesByUserId(String userid);
    List<String> listRolesIdByUserId(String userid);
}
