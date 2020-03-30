package indi.wyx0k.story.core.mapper;

import indi.wyx0k.story.core.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Repository;

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
    List<String> listRoleId (String userId);
}