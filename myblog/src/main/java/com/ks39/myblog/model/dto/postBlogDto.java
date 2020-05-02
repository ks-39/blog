package com.ks39.myblog.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 11:37 2020/4/26
 */
@Data
public class postBlogDto {

    private Long blog_id ;

    @NotNull
    private String blog_title;

    @NotNull
    private String blog_content;

    private String blog_description;

    private String blog_cover;

    //tinyint
    private Byte blog_status;

    //tinyint
    private Byte is_comment;

    @NotNull
    private Long categoryId;

    private String tags ;
}
