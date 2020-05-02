package com.ks39.myblog.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 11:35 2020/4/21
 */
@Data
public class view {
    private Integer id;

    private String ip;

    private Date create_time;

    private String browser;

    private String system;

    private Integer count;
}
