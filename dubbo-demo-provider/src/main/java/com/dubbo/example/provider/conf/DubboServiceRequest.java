package com.dubbo.example.provider.conf;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ChangJie on 2020-05-08.
 */
@Data
@Builder
public class DubboServiceRequest implements Serializable {

    /**
     * 接口名
     */
    private String interfaceName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数
     */
    private Object[] args;

}
