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
 * 角色
 * </p>
 *
 * @author wyx0k
 * @since 2020-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("story_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID=1L;

    /**
     * 角色id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 角色名称
     */
    @TableField("roleName")
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色拥有的许可的id
     */
    @TableField("permissionId")
    private String permissionId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
