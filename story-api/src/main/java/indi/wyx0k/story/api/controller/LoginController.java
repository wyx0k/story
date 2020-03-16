package indi.wyx0k.story.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.wyx0k.story.core.common.BaseRequest;
import indi.wyx0k.story.core.entity.Gun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * story
 *
 * @Author wyx0k
 * 2019/11/10
 **/
@RequestMapping("/api_1_0")
@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @PostMapping("/login")
    private Gun login(@RequestBody String req){
        JSONObject jsonObject = JSON.parseObject(req);
        String name = jsonObject.getString("name");
        String pass = jsonObject.getString("pass");
        logger.info("name:{},pass{}",name,pass);
        Gun gun = new Gun();
        gun.setName("short gun");
        gun.setRange(50);
        return gun;
    }
}
