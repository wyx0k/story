package indi.wyx0k.story.service.image.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 图片
 * </p>
 *
 * @author wyx0k
 * @since 2020-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("story_image")
public class Image extends Model<Image> {

    private static final long serialVersionUID=1L;

    /**
     * 图片id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图片名称
     */
    @TableField("imageName")
    private String imageName;

    /**
     * 存储路径
     */
    private String path;

    /**
     * 图片原名
     */
    @TableField("originName")
    private String originName;

    /**
     * 对应的信息id
     */
    @TableField("infoId")
    private Integer infoId;

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
     * 所属用户
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 只有所属用户可见
     */
    @TableField("userOnly")
    private Boolean userOnly;

    /**
     * 游客许可-游客是否可见-默认没有
     */
    @TableField("guestPermission")
    private Boolean guestPermission;

    /**
     * 缩略图地址
     */
    @TableField("thumnailPath")
    private String thumnailPath;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
