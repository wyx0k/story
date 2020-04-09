package indi.wyx0k.story.service.image.service.impl;

import indi.wyx0k.story.service.image.entity.ImageInfo;
import indi.wyx0k.story.service.image.mapper.ImageInfoMapper;
import indi.wyx0k.story.service.image.service.IImageInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图片相关信息 服务实现类
 * </p>
 *
 * @author wyx0k
 * @since 2020-04-09
 */
@Service
public class ImageInfoServiceImpl extends ServiceImpl<ImageInfoMapper, ImageInfo> implements IImageInfoService {

}
