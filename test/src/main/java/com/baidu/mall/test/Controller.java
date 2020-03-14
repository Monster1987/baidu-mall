package com.baidu.mall.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Controller {
    @RequestMapping(value = {"/",""})
    public String hellTask(@Value("${myName}")String username ){

        return "hello  my current myName is " + username;
    }

}
