package indi.wyx0k.story.service.image.service;

import indi.wyx0k.story.service.image.entity.ImageInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 图片相关信息 服务类
 * </p>
 *
 * @author wyx0k
 * @since 2020-04-09
 */
public interface IImageInfoService extends IService<ImageInfo> {
    ImageInfo getImageInfoById(int id);
    void deleteImageInfoById(int id);
    void addImageInfo(ImageInfo imageInfo);
    void updateImageInfo(ImageInfo imageInfo);
}
