package indi.wyx0k.story.service.user.mapper;

import indi.wyx0k.story.service.user.entity.UserRole;
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
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<Integer> listRoleId (int userId);
}
