package indi.wyx0k.story.service.image.dto;

import indi.wyx0k.story.service.image.entity.Image;
import indi.wyx0k.story.service.image.entity.ImageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/10
 */
@Data
@Accessors
@AllArgsConstructor
public class ImageDto {
    private Image image;
    private ImageInfo imageInfo;
}
