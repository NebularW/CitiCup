package com.flower.controller;

import com.flower.service.ScheduledCalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: 用于进行接口测试
 * @Author: sky
 * @Date: 2022/3/4 19:34
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @Autowired
    ScheduledCalService scheduledCalService;

    @RequestMapping("/cal")
    public String cal(){
        scheduledCalService.calculate();
        return "done!";
    }

}
