package indi.wyx0k.story.service.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.wyx0k.story.service.user.entity.User;
import indi.wyx0k.story.service.user.mapper.UserMapper;
import indi.wyx0k.story.service.user.service.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author wyx0k
 * @since 2020-03-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User getUserByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        User user = getBaseMapper().selectOne(queryWrapper);
        return user;
    }

    @Override
    public void updateUser(User user) {
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setModifyTime(localDateTime);
        getBaseMapper().updateById(user);
    }

    @Override
    public void deleteUserById(int userId) {
        getBaseMapper().deleteById(userId);
    }

    @Override
    public void addUser(User user) {
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setCreateTime(localDateTime);
        user.setModifyTime(localDateTime);
        getBaseMapper().insert(user);
    }
}
