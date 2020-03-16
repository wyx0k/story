package indi.wyx0k.story.core.common;

import lombok.Data;

/**
 * story
 * --
 *
 * @author wyx
 * --基础返回对象
 * 2020/3/17
 */
@Data
public class BaseResponse {
    /**
     * 标记
     */
    private String token;
    /**
     * 状态码
     */
    private String code;
    /**
     * 内容
     */
    private String msg;
}
