package com.ks39.myblog.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 17:55 2020/4/14
 */
@Data
public class blog {

    private Long blog_id;

    private String blog_title;

    private String blog_description;

    private String blog_cover;

    private String blog_content;

    //tinyint
    private Byte blog_status;

    private Long blog_view;

    //tinyint
    private Byte is_comment;

    private Date create_time;

    private Date update_time;


    //额外：分类属性
    private String category_name;

    private Long category_id;

    //统计博客数量
    private Integer count;

}
