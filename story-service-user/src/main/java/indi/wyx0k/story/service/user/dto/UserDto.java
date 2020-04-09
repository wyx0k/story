package indi.wyx0k.story.service.user.dto;

import indi.wyx0k.story.service.user.entity.User;
import indi.wyx0k.story.service.user.entity.UserInfo;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/9
 */
@Data
@AllArgsConstructor
@Accessors
public class UserDto {
    private User user;
    private UserInfo userInfo;

}
