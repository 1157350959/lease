package com.atguigu.lease.common.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: MinioProperties
 * Package: com.atguigu.lease.common.minio
 * Description:
 *
 * @Author HL
 * @Create 11/4/2024 11:53 AM
 * @Version:
 */
@Data
@ConfigurationProperties(prefix="minio")
public class MinioProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    public String bucketName;
}
