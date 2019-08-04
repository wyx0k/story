package indi.wyx0k.story.api.controller;


import com.alibaba.fastjson.JSON;
import indi.wyx0k.story.core.entity.Gun;
import indi.wyx0k.story.core.security.crypto.BaseCryptoWrap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Test2Controller {
    @GetMapping("/t2")
    public String t2(){
        Gun gun = new Gun();
        gun.setName("123");
        BaseCryptoWrap baseCryptoWrap = new BaseCryptoWrap();
        baseCryptoWrap.setBody(JSON.toJSONString(gun));
        return JSON.toJSONString(baseCryptoWrap);
    }
}
