package indi.wyx0k.story.core.entity;

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
 * 许可
 * </p>
 *
 * @author wyx0k
 * @since 2020-03-30
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

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("modifyTime")
    private LocalDateTime modifyTime;

    /**
     * 中文名
     */
    @TableField("zhName")
    private String zhName;

    /**
     * 名称
     */
    private String name;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
