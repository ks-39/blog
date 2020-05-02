package com.ks39.myblog.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 13:13 2020/4/14
 */
@Data
public class tag {

    private Long tag_id;

    private String tag_name;

    private Date create_time;

    private Date update_time;

    //额外属性：标签下博客数量
    private Integer count;
}
