package com.atguigu.lease.common.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: AliyunSMSProperties
 * Package: com.atguigu.lease.common.sms
 * Description:
 *
 * @Author HL
 * @Create 11/7/2024 2:01 PM
 * @Version:
 */
@Data
@ConfigurationProperties(prefix="aliyun.sms")
public class AliyunSMSProperties {
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
}
