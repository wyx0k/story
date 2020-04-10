package indi.wyx0k.story.service.image.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import indi.wyx0k.story.core.common.BaseResponse;
import indi.wyx0k.story.core.security.crypto.MD5Util;
import indi.wyx0k.story.core.util.ResponseUtil;
import indi.wyx0k.story.service.image.config.ImageConfig;
import indi.wyx0k.story.service.image.dto.ImageDto;
import indi.wyx0k.story.service.image.entity.Image;
import indi.wyx0k.story.service.image.entity.ImageInfo;
import indi.wyx0k.story.service.image.service.IImageInfoService;
import indi.wyx0k.story.service.image.service.IImageService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 图片 前端控制器
 * </p>
 *
 * @author wyx0k
 * @since 2020-04-09
 */
@Slf4j
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageConfig imageConfig;
    @Autowired
    private IImageService iImageService;
    @Autowired
    private IImageInfoService iImageInfoService;
    @Autowired
    private ObjectMapper objectMapper;
    @PostMapping("/list/{userId}")
    public BaseResponse listImageByUser(@PathVariable("userId") int userId,@RequestParam int page,@RequestParam int size){
        Page<Image> page1 = iImageService.listImageByUser(userId,page,size);
        return ResponseUtil.success("",page1);
    }
    @PostMapping("/list")
    public BaseResponse listImageByTime(@RequestParam String fromdate,@RequestParam String todate,@RequestParam int page,@RequestParam int size){
        Page<Image> page1 = iImageService.listImageByTime(fromdate,todate,page,size);
        if(null == page1){
            return ResponseUtil.failure("","图片查询失败");
        }
        return ResponseUtil.success("",page1);
    }
    @PostMapping("/{imageName}")
    public BaseResponse getImage(@PathVariable String imageName){
        return ResponseUtil.success("",iImageService.listImageLikeOriginName(imageName));
    }
    @Transactional
    @PostMapping("/add")
    public BaseResponse addImage(@RequestParam MultipartFile image, @RequestParam String imageDtoStr){
        ImageDto imageDto = null;
        try {
            imageDto = objectMapper.readValue(imageDtoStr, ImageDto.class);
        } catch (JsonProcessingException e) {
            log.warn("获取参数失败，请检查请求参数json");
            return ResponseUtil.failure("","获取参数失败，请检查请求参数json");
        }
        String originName = image.getOriginalFilename();
        String path = getImagePath(originName,false);
        String thumbPath = getImagePath(originName,true);
        File file = new File(path);
        File thumbnailFile = new File(thumbPath);
        try {
            image.transferTo(file);
            Thumbnails.of(file).size(200,300).toFile(thumbnailFile);
        } catch (IOException e) {
            log.warn("图片传输失败~,{}",e.getMessage());
            throw new RuntimeException("图片传输失败~");
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        Image image1 = imageDto.getImage();
        ImageInfo imageInfo = imageDto.getImageInfo();

        if(null == image1){
            image1 = new Image();
            image1.setCreateTime(localDateTime);
            image1.setModifyTime(localDateTime);
            image1.setOriginName(originName);
            image1.setImageName(file.getName());
            image1.setGuestPermission(false);
            image1.setPath(path);
            image1.setUserOnly(false);
            image1.setThumnailPath(thumbPath);
        }else {
            image1.setCreateTime(localDateTime);
            image1.setModifyTime(localDateTime);
            image1.setOriginName(originName);
            image1.setImageName(file.getName());
            image1.setPath(path);
            image1.setThumnailPath(thumbPath);
        }
        iImageService.addImage(image1);
        if(null != imageInfo ){
            iImageInfoService.addImageInfo(imageInfo);
            image1.setInfoId(imageInfo.getId());
            image1.insertOrUpdate();
        }
        return ResponseUtil.success("","图片添加成功");
    }
    @Transactional
    @PostMapping("/{imageTrueName}/delete")
    public BaseResponse deleteImage(@PathVariable String imageTrueName){
        Image image = iImageService.getImageByImageName(imageTrueName);
        ImageInfo imageInfo = iImageInfoService.getImageInfoById(image.getInfoId());
        if( null == image){
            return ResponseUtil.failure("","啊哦,图片不存在~");
        }
        iImageService.deleteImageByImageName(image.getId());
        if(null != imageInfo){
            iImageInfoService.deleteImageInfoById(imageInfo.getId());
        }
        return ResponseUtil.success("","图片删除成功");
    }

    private String getImagePath(String originName,boolean thumbnail){
        LocalDateTime localDateTime = LocalDateTime.now();
        UUID uuid = UUID.randomUUID();
        int hashCode = (uuid+originName).hashCode();
        String imageName = System.currentTimeMillis()+"-"+hashCode+"-"+originName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(imageConfig.getPath())
                .append(File.separator);
        if(thumbnail){
            stringBuffer.append("thumbnail").append(File.separator);
        }else {
            stringBuffer.append("images").append(File.separator);
        }
        stringBuffer.append(localDateTime.getYear()+"")
                .append(File.separator)
                .append(localDateTime.getMonthValue()+"");
        File file = new File(stringBuffer.toString());
        if(!file.exists()){
            file.mkdirs();
        }
        stringBuffer.append(File.separator)
                .append(imageName);
        return stringBuffer.toString();
    }
}

