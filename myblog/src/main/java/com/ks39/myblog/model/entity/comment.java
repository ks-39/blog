package com.ks39.myblog.model.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 19:31 2020/4/16
 */
@Data
public class comment {

    private Long comment_id;

    private Long blog_id;

    private String comment_ip;

    private Long parent_comment_id;

    private String reply_to;

    private String comment_name;

    private String comment_email;

    private String comment_content;

    private Integer comment_status;

    private Date create_time;

    private Date update_time;

    //额外属性：根据parent_comment_id查询子comment
    private List<comment> subComments;

    //统计评论数量
    private Integer count;

    private String blog_title;
}
