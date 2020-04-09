package indi.wyx0k.story.service.user.service;

import indi.wyx0k.story.service.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author wyx0k
 * @since 2020-03-30
 */
public interface IUserService extends IService<User> {
    User getUserByUsername(String username);
    void updateUser(User user);
    void deleteUserById(int userId);
    void addUser(User user);
}
