package indi.wyx0k.story.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.wyx0k.story.core.entity.Memo;
import indi.wyx0k.story.core.entity.Meta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * story
 *
 * @Author wyx0k
 * 2019/12/8
 **/
@RequestMapping("/api_1_0")
@RestController
public class MemoController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @GetMapping("/memos")
    private List<Memo> login(@RequestParam Integer size,@RequestParam Integer start){
        logger.info("size:{},start:{}",size,start);
        List<Memo> memos = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            Memo memo = new Memo();
            memo.setId(i+"");
            memo.setDescription("----"+i+"----");
            memo.setPhotoURL("asdasdasdasda");
            memo.setTitle("==="+i+"===");
            memo.setUserId("user"+i);
            memo.setCreateTime("2131-09-09");
            memo.setShowToOther(false);
            memo.setModifyTime("2131-09-09");
            memo.setLocation("武汉");
            memo.setEmotion(5);
            memos.add(memo);
        }
        return memos;
    }
    @GetMapping("/memos/meta")
    private Meta memosMeta(){
        Meta meta = new Meta();
        meta.setTotalSize(120);
        return meta;
    }
}
