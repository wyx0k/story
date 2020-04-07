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
 * @since 2020-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("story_user_role")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID=1L;

      @TableId(value = "userId", type = IdType.AUTO)
    private Integer userId;

    @TableField("roleId")
    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
