package indi.wyx0k.story.service.user.controller;

import indi.wyx0k.story.core.annotation.EventCommand;
import indi.wyx0k.story.core.common.BaseResponse;
import indi.wyx0k.story.core.annotation.Command;
import indi.wyx0k.story.core.annotation.ReturnEvent;
import indi.wyx0k.story.core.annotation.StoryController;
import indi.wyx0k.story.core.util.ResponseUtil;
import indi.wyx0k.story.service.user.dto.UserDto;
import indi.wyx0k.story.service.user.entity.User;
import indi.wyx0k.story.service.user.entity.UserInfo;
import indi.wyx0k.story.service.user.service.IUserInfoService;
import indi.wyx0k.story.service.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/9
 */
@StoryController
public class StoryUserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserInfoService userInfoService;
    @ReturnEvent("listUser")
    public BaseResponse listUser(){
        List<User> users = userService.list();
        users.stream().forEach(user->user.setPassword("[******]"));
        return ResponseUtil.success("",users);
    }
    @ReturnEvent("getUser")
    public BaseResponse getUser(String username){
        User user = userService.getUserByUsername(username);
        if(null == user){
            return ResponseUtil.failure("","没有找到该用户哦~");
        }
        user.setPassword("[******]");
        return ResponseUtil.success("",user);
    }
    @Command(value = "newUser",order = 1)
    @ReturnEvent("getUserDetail")
    public BaseResponse getUserDetail(String username){
        User user = userService.getUserByUsername(username);
        if(null == user){
            return ResponseUtil.failure("","没有找到该用户哦~");
        }
        user.setPassword("[******]");
        UserInfo userInfo = userInfoService.getUserInfoByUserId(user.getId());
        UserDto userDto = new UserDto(user,userInfo);
        return ResponseUtil.success("",userDto);
    }
    @Transactional
    @Command(value = "newUser",order = 0)
    @ReturnEvent("addUser")
    public BaseResponse addUser(UserDto userDto){
        User user = userDto.getUser();
        UserInfo userInfo = userDto.getUserInfo();
        User userData = userService.getUserByUsername(user.getUsername());
        if(null != userData){
            return ResponseUtil.failure("","用户名已存在~");
        }
        userService.addUser(user);
        if(null != userInfo){
            userInfo.setUserId(user.getId());
            userInfoService.addUserInfo(userInfo);
            user.setUserInfoId(userInfo.getId());
            userService.updateUser(user);
        }
        return ResponseUtil.success("","创建用户成功");
    }
    @Transactional
    @ReturnEvent("deleteUser")
    public BaseResponse deleteUser(String username){
        User user = userService.getUserByUsername(username);
        if(null == user){
            return ResponseUtil.failure("","没有找到该用户哦~");
        }
        userService.deleteUserById(user.getId());
        UserInfo userInfo = userInfoService.getUserInfoByUserId(user.getId());
        userInfoService.deleteUserInfoById(userInfo.getId());
        return ResponseUtil.success("","删除用户成功~");
    }
    @Transactional
    @ReturnEvent("updateUser")
    public BaseResponse updateUser(UserDto userDto){
        User userIn = userDto.getUser();
        UserInfo userInfoIn = userDto.getUserInfo();
        User user = userService.getUserByUsername(userIn.getUsername());
        if(null == user){
            return ResponseUtil.failure("","没有找到该用户哦~");
        }
        UserInfo userInfo = userInfoService.getUserInfoByUserId(user.getId());
        userIn.setId(user.getId());
        userInfoIn.setId(userInfo.getId());
        userService.updateUser(userIn);
        userInfoService.updateUserInfo(userInfo);
        return ResponseUtil.success("","更新个人信息成功~");
    }
    @EventCommand("freezeUser")
    public BaseResponse freezeUser(String username){
        User user = userService.getUserByUsername(username);
        if(null == user){
            return ResponseUtil.failure("","没有找到该用户哦~");
        }
        user.setFreeze(true);
        userService.updateUser(user);
        return ResponseUtil.success("","用户["+username+"]已成功冻结~");
    }
    @EventCommand("unfreezeUser")
    public BaseResponse unfreezeUser(String username){
        User user = userService.getUserByUsername(username);
        if(null == user){
            return ResponseUtil.failure("","没有找到该用户哦~");
        }
        user.setFreeze(false);
        userService.updateUser(user);
        return ResponseUtil.success("","用户["+username+"]已成功解冻~");
    }
}
