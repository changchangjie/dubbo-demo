package com.dubbo.example.provider.conf;

import com.alibaba.fastjson.JSON;
import com.dubbo.example.provider.DemoServiceImpl;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChangJie on 2020-05-09.
 */
public class DubboServiceFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(DubboServiceFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        //打印入参日志
        DubboServiceRequest serviceRequest = DubboServiceRequest.builder()
                .interfaceName(invocation.getInvoker().getInterface().getName())
                .methodName(invocation.getMethodName())
                .args(invocation.getArguments())
                .build();
        log.info("dubbo服务接口入参: " + JSON.toJSONString(serviceRequest));

        //开始时间
        long startTime = System.currentTimeMillis();
        //执行接口调用逻辑
        Result result = invoker.invoke(invocation);
        //调用耗时
        long elapsed = System.currentTimeMillis() - startTime;


        //如果发生异常 则打印异常日志
        if (result.hasException() && invoker.getInterface() != GenericService.class) {
            log.error("dubbo执行异常: ", result.getException());
        } else {
            //打印响应日志
            DubboServiceResponse serviceResponse = DubboServiceResponse.builder()
                    .methodName(invocation.getMethodName())
                    .interfaceName(invocation.getInvoker().getInterface().getName())
                    .args(invocation.getArguments())
                    .result(new Object[]{result.getValue()})
                    .rt(elapsed).build();
            log.info("dubbo服务响应成功,返回数据: " + JSON.toJSONString(serviceResponse));
        }
        //返回结果响应结果
        return result;
    }
}
