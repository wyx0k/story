package indi.wyx0k.story.service.image.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indi.wyx0k.story.service.image.entity.Image;
import indi.wyx0k.story.service.image.mapper.ImageMapper;
import indi.wyx0k.story.service.image.service.IImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>
 * 图片 服务实现类
 * </p>
 *
 * @author wyx0k
 * @since 2020-04-09
 */
@Slf4j
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {
    @Override
    public Page<Image> listImageByUser(int userId, int page, int size) {
        Page<Image> resultPage = new Page<>(page,size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userId",userId);
        resultPage = getBaseMapper().selectPage(resultPage,queryWrapper);

        return resultPage;
    }

    @Override
    public Page<Image> listImageByTime(String fromdate, String todate, int page, int size) {
        Page<Image> resultPage = new Page<>(page,size);
        QueryWrapper queryWrapper = new QueryWrapper();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            queryWrapper.between("createTime",simpleDateFormat.parse(fromdate),simpleDateFormat.parse(todate));
        } catch (ParseException e) {
            log.warn("图片查询失败，请检查参数：fromdate:{}，todate:{}，page:{}，size:{}",fromdate,todate,page,size);
            return null;
        }
        resultPage = getBaseMapper().selectPage(resultPage,queryWrapper);

        return resultPage;
    }

    @Override
    public List<Image> listImageByOriginName(String imageName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("originName",imageName);
        List<Image> list = getBaseMapper().selectList(queryWrapper);
        return list;
    }

    @Override
    public List<Image> listImageLikeOriginName(String imageName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("originName",imageName);
        List<Image> list = getBaseMapper().selectList(queryWrapper);
        return list;
    }

    @Override
    public void deleteImageByImageName(int imageId) {
        getBaseMapper().deleteById(imageId);

    }

    @Override
    public Image getImageByImageName(String imageName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("imageName",imageName);
        return getBaseMapper().selectOne(queryWrapper);
    }

    @Override
    public void addImage(Image image) {
        getBaseMapper().insert(image);
    }
}
