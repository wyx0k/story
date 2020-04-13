package indi.wyx0k.story.core.exeception;

import com.baomidou.mybatisplus.extension.api.R;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/13
 */
public class StoryEngineClientInitialExeception extends RuntimeException {
    public StoryEngineClientInitialExeception() {
    }
    public StoryEngineClientInitialExeception(String message) {
        super(message);
    }
}
