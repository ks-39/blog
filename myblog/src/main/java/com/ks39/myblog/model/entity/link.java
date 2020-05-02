package com.ks39.myblog.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 17:34 2020/4/13
 */
@Data
public class link {

    private Long link_id;

    private String link_name;

    private String link_url;

    private Date create_time;

    private Date update_time;
}
