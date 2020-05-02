package com.ks39.myblog.util;

import com.ks39.myblog.service.qiniuService;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 自动装配条件为IQiniuService这个存在classpath路径下
@ConditionalOnClass(qiniuService.class)
// 当配置文件中 qinniu = true时 实例化此类，默认为true
@ConditionalOnProperty(prefix = "qiniu",value = "true",matchIfMissing = true)
public class qiniuConfig {

    @Autowired
    private qiniuProperties qiniuProperties ;

    @Bean
    public com.qiniu.storage.Configuration region(){
        switch (qiniuProperties.getZone()){
            case "huadong": return new com.qiniu.storage.Configuration(Region.region0());
            case "huabei": return new com.qiniu.storage.Configuration(Region.region1());
            case "huanan": return new com.qiniu.storage.Configuration(Region.region2());
            case "beimei": return new com.qiniu.storage.Configuration(Region.regionNa0());
            case "dongnanya": return new com.qiniu.storage.Configuration(Region.regionAs0());
            default:{
                throw new RuntimeException("Zone配置错误(例:huadong、huanan、beimei、dongnanya....)") ;
            }
        }
    }

    /**
     * 构建一个七牛上传工具实例
     */
    @Bean
    public UploadManager uploadManager(@Qualifier("region") com.qiniu.storage.Configuration region){
        return new UploadManager(region);
    }

    /**
     * 认证信息实例
     */
    @Bean
    public Auth auth(){
        return Auth.create(qiniuProperties.getAccessKey(),qiniuProperties.getSecretKey()) ;
    }

    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager(@Qualifier("auth") Auth auth,
                                       @Qualifier("region") com.qiniu.storage.Configuration region){
        return new BucketManager(auth,region);

    }

}
