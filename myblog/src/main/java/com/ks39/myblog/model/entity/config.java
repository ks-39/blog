package com.ks39.myblog.model.entity;

import lombok.Data;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 13:01 2020/4/13
 */
@Data       //getter()/setter()
public class config {

    private String background_image;

    private String head_title;

    private String head_subtitle;

    private String head_info;

    private String avator;

    private String nick_name;

    private String introduction;

    private String wechat_image;

    private String footer_info;
}
