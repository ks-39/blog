package com.ks39.myblog.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 12:56 2020/4/21
 */
@Data
public class log {

    private Integer id;

    private String ip;

    private String HTTP_URL;

    private String HTTP_TYPE;

    private String CLASS_METHOD;

    private String CLASS_METHOD_ARGS;

    private String METHOD_RESPONSE;

    private Date create_time;
}
