package com.dubbo.example.provider.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by ChangJie on 2020-05-08.
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public AccessLimitInterceptor accessLimitInterceptor(){
        return new AccessLimitInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLimitInterceptor());
    }
}
