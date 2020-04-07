package indi.wyx0k.story.service.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.wyx0k.story.service.user.entity.User;
import indi.wyx0k.story.service.user.mapper.UserMapper;
import indi.wyx0k.story.service.user.service.IUserService;
import org.springframework.stereotype.Service;

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

}
