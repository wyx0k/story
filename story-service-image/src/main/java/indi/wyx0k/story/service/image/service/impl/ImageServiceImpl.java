package indi.wyx0k.story.service.image.service.impl;

import indi.wyx0k.story.service.image.entity.Image;
import indi.wyx0k.story.service.image.mapper.ImageMapper;
import indi.wyx0k.story.service.image.service.IImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图片 服务实现类
 * </p>
 *
 * @author wyx0k
 * @since 2020-04-09
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {

}
