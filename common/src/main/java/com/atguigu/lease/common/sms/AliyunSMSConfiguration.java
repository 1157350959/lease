package com.atguigu.lease.common.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: AliyunSMSConfiguration
 * Package: com.atguigu.lease.common.sms
 * Description:
 *
 * @Author HL
 * @Create 11/7/2024 2:02 PM
 * @Version:
 */
@Configuration
@ConditionalOnProperty(name="aliyun.sms.endpoint")
@EnableConfigurationProperties(AliyunSMSProperties.class)
public class AliyunSMSConfiguration {
    @Autowired
    private AliyunSMSProperties aliyunSMSProperties;

    @Bean
    public Client createClient(){
        Config config = new Config();
        config.setAccessKeyId(aliyunSMSProperties.getAccessKeyId());
        config.setAccessKeySecret(aliyunSMSProperties.getAccessKeySecret());
        config.setEndpoint(aliyunSMSProperties.getEndpoint());
        try {
            return new Client(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
