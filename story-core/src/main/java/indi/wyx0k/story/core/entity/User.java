package indi.wyx0k.story.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyx0k
 * @since 2020-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("story_user")
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色id
     */
    @TableField("roleId")
    private String roleId;

    /**
     * 用户详细信息的id
     */
    @TableField("userInfoId")
    private String userInfoId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
