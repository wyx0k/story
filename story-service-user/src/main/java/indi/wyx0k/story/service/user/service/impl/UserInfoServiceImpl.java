package indi.wyx0k.story.service.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.wyx0k.story.service.user.entity.UserInfo;
import indi.wyx0k.story.service.user.mapper.UserInfoMapper;
import indi.wyx0k.story.service.user.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyx0k
 * @since 2020-04-09
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    @Override
    public UserInfo getUserInfoByUserId(int userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userId",userId);
        UserInfo userInfo = getBaseMapper().selectOne(queryWrapper);
        return userInfo;
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        getBaseMapper().updateById(userInfo);
    }

    @Override
    public void deleteUserInfoById(int id) {
        getBaseMapper().deleteById(id);
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        getBaseMapper().insert(userInfo);
    }
}
