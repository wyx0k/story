package indi.wyx0k.story.service.image.entity;

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
 * 图片相关信息
 * </p>
 *
 * @author wyx0k
 * @since 2020-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("story_image_info")
public class ImageInfo extends Model<ImageInfo> {

    private static final long serialVersionUID=1L;

    /**
     * 图片信息id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图片描述
     */
    private String description;

    /**
     * 标题
     */
    private String title;

    /**
     * 拍摄大地横坐标
     */
    @TableField("locationX")
    private String locationX;

    /**
     * 拍摄大地纵坐标
     */
    @TableField("locationY")
    private String locationY;

    /**
     * 心情
     */
    private Integer emotion;

    /**
     * 大地坐标系id-用于标明是什么坐标系
     */
    private String wkid;

    /**
     * 分类标签
     */
    private String tag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
