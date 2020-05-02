package com.ks39.myblog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Ks-39
 * @Description: 七牛云图片实体类
 * @Date: Create in 23:00 2020/4/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class imageInfo {

    private String url;
    private String name;
    private double size;
    private String create_time;

}
