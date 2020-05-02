package com.ks39.myblog.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 22:39 2020/4/21
 */
@Component("qiniu")
@ConfigurationProperties(prefix = "qiniu")
@Data
public class qiniuProperties {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String zone;

    private String domain;

}
