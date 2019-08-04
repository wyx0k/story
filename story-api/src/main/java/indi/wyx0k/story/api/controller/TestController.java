package indi.wyx0k.story.api.controller;

import indi.wyx0k.story.core.entity.Gun;
import indi.wyx0k.story.core.security.crypto.BaseCryptoWrap;
import indi.wyx0k.story.core.security.crypto.annotation.CryptoMethod;
import indi.wyx0k.story.core.security.crypto.enumeration.CryptoType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @CryptoMethod(value = CryptoType.RSA)
    @PostMapping("/t1")
    public Object test1(@RequestBody BaseCryptoWrap baseCryptoWrap){
        Gun gun = new Gun();
        gun.setName("reerer");
        return gun.toString();
    }
}
