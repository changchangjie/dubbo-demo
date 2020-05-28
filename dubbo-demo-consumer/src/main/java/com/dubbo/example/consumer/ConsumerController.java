package com.dubbo.example.consumer;

import com.dubbo.example.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ChangJie on 2020-05-09.
 */
@RestController
public class ConsumerController {

    @Reference(version = "1.0.0")
    private DemoService demoService;

    @GetMapping(value = "consumer")
    public String consumer(){
        String response = demoService.sayHello("Provider");
        return response;
    }
}
