package com.atguigu.lease.common.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MinioConfiguration
 * Package: com.atguigu.lease.common.minio
 * Description:
 *
 * @Author HL
 * @Create 11/4/2024 11:50 AM
 * @Version:
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
@ConditionalOnProperty(name="minio.endpoint")
public class MinioConfiguration {
    @Autowired
    private MinioProperties p;
    @Bean
    public MinioClient minioClient() {
        return MinioClient
                .builder()
                .endpoint(p.getEndpoint())
                .credentials(p.getAccessKey(), p.getSecretKey())
                .build();
    }
}
