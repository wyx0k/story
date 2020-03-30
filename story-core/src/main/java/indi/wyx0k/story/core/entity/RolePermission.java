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
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("story_role_permission")
public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID=1L;

      @TableId(value = "roleId", type = IdType.AUTO)
    private String roleId;

    @TableField("permissionId")
    private String permissionId;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
