package indi.wyx0k.story.service.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import indi.wyx0k.story.service.user.entity.UserInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyx0k
 * @since 2020-04-09
 */
public interface IUserInfoService extends IService<UserInfo> {
    UserInfo getUserInfoByUserId(int userId);
    void updateUserInfo(UserInfo userInfo);
    void deleteUserInfoById(int id);
}
