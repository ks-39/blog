package com.ks39.myblog.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 14:16 2020/4/14
 */
@Data
public class category {

    private Long category_id;

    private String category_name;

    private Date create_time;

    private Date update_time;

    //额外属性：统计分类下的博客数量
    private Integer count;
}
