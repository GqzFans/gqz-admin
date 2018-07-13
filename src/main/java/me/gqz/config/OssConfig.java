package me.gqz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = OssConfig.OSS_CONF)
public class OssConfig {

    final static String OSS_CONF = "oss_conf";
    /**
     * OSS 访问域名[内网]

     */
    private String endpoint;

    /**
     * OSS 访问域名[外网]
     */
    private String outEndpoint;
    /**
     * key ID
     */
    private String accessKeyId;

    /**
     * key密钥
     */
    private String accessKeySecret;

    /**
     * 文件服务器根目录
     */
    private String bucketName;

    /**
     * 延迟生效时间 单位:h
     */
    private int delayHour;

    /**
     * 远端路径
     */
    private String remotePath;

    /**
     * 合同路径
     */
    private String orderContract;

    /**
     * Bucket
     */
    private String bucket;
}
