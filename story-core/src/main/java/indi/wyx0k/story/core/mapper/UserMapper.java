package indi.wyx0k.story.core.mapper;

import indi.wyx0k.story.core.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author wyx0k
 * @since 2020-03-30
 */
public interface UserMapper extends BaseMapper<User> {

}
