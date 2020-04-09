package indi.wyx0k.story.service.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
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
 * @since 2020-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("story_user_info")
public class UserInfo extends Model<UserInfo> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private LocalDateTime birthday;

    private String hoby;

    private String phone;

    private String usericon;

    private String music;

    @TableField("familyRole")
    private String familyRole;

    @TableField("familyId")
    private Integer familyId;

    @TableField("userId")
    private Integer userId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
