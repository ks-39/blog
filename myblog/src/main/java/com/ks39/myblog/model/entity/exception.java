package com.ks39.myblog.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 14:31 2020/4/21
 */
@Data
public class exception {

    private Integer id;

    private Integer exception_code;

    private String exception_message;

    private Date create_time;

    private Integer count;
}
