package indi.wyx0k.story.core.service.impl;

import indi.wyx0k.story.core.entity.User;
import indi.wyx0k.story.core.mapper.UserMapper;
import indi.wyx0k.story.core.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
