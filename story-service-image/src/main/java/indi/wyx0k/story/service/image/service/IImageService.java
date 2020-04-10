package indi.wyx0k.story.service.image.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indi.wyx0k.story.service.image.entity.Image;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 图片 服务类
 * </p>
 *
 * @author wyx0k
 * @since 2020-04-09
 */
public interface IImageService extends IService<Image> {
    Page<Image> listImageByUser(int userId, int page, int size);
    Page<Image> listImageByTime(String fromdate, String todate, int page, int size);
    List<Image> listImageByOriginName(String originName);
    List<Image> listImageLikeOriginName(String originName);
    void deleteImageByImageName(int imageId);
    Image getImageByImageName(String imageName);
    void addImage(Image image);
}
