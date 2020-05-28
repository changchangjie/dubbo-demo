package com.dubbo.example.provider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ChangJie on 2020-05-08.
 */
@RestController
public class DemoController {

    @GetMapping(value = "test")
    @SentinelResource(value = "test")
    public String test(){
        return "test";
    }
}
