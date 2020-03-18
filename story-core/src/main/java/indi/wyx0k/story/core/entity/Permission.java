package indi.wyx0k.story.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("story_permission")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID=1L;

    /**
     * 许可id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 许可的路径
     */
    private String path;

    /**
     * 许可描述
     */
    private String description;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
