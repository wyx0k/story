package indi.wyx0k.story.service.user.controller;

import indi.wyx0k.story.core.common.BaseResponse;
import indi.wyx0k.story.core.util.ResponseUtil;
import indi.wyx0k.story.service.user.dto.UserDto;
import indi.wyx0k.story.service.user.entity.User;
import indi.wyx0k.story.service.user.entity.UserInfo;
import indi.wyx0k.story.service.user.service.IUserInfoService;
import indi.wyx0k.story.service.user.service.IUserService;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/9
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserInfoService userInfoService;
    @PostMapping("/list")
    public BaseResponse listUser(){
        List<User> users = userService.list();
        users.stream().forEach(user->user.setPassword("[******]"));
        return ResponseUtil.success("",users);
    }
    @PostMapping("/{username}")
    public BaseResponse getUser(@PathVariable("username")String username){
        User user = userService.getUserByUsername(username);
        if(null == user){
            return ResponseUtil.failure("","没有找到该用户哦~");
        }
        user.setPassword("[******]");
        return ResponseUtil.success("",user);
    }
    @PostMapping("/{username}/detail")
    public BaseResponse getUserDetail(@PathVariable("username")String username){
        User user = userService.getUserByUsername(username);
        if(null == user){
            return ResponseUtil.failure("","没有找到该用户哦~");
        }
        user.setPassword("[******]");
        UserInfo userInfo = userInfoService.getUserInfoByUserId(user.getId());
        UserDto userDto = new UserDto(user,userInfo);
        return ResponseUtil.success("",userDto);
    }
    @PostMapping("/add")
    public BaseResponse addUser(@RequestBody UserDto userDto){
        List<User> users = userService.list();
        return ResponseUtil.success("",users);
    }
    @PostMapping("/{username}/delete")
    public BaseResponse deleteUser(@PathVariable("username")String username){
        User user = userService.getUserByUsername(username);
        if(null == user){
            return ResponseUtil.failure("","没有找到该用户哦~");
        }
        userService.deleteUserById(user.getId());
        UserInfo userInfo = userInfoService.getUserInfoByUserId(user.getId());
        userInfoService.deleteUserInfoById(userInfo.getId());
        return ResponseUtil.success("","删除用户成功~");
    }
    @PostMapping("/update")
    public BaseResponse updateUser(@RequestBody UserDto userDto){
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
}
