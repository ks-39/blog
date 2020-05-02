package com.ks39.myblog.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Ks-39
 * @Description: 用于存储分页插件参数和跳转的参数
 * @Date: Create in 22:48 2020/4/14
 */
@Data
public class ParamDto {

    /*
    URL的param：articleId、categoryId、tagId、search
     */
    //1.文章的Id(进入某一篇博客页面)
    private Long blogId;

    //2. 分类Id(某一分类下的博客列表)
    private Long categoryId;

    //3. 标签Id(某一标签下的博客列表)
    private Long tagId;

    //4. 搜索框(搜索符合条件的博客列表)
    private String search;


    /*
    封装pageInfo参数，用于控制分页插件
     */
    //1. 需要展示第几页的博客列表
    private Integer blogPage;

    //2. 每页展示多少篇博客
    private Integer blogPageSize;

    //3. 分页导航页数显示5页,
    private Integer navigatePages;


    /*
    后台管理分页参数
     */
    private Integer page;

    private Integer size;


    //3. 设置默认pageInfo参数
    public ParamDto(){
        this.navigatePages = 5;
        this.blogPage = 1;
        this.blogPageSize = 5;

        this.commentPage = 1;
        this.commentPageSize = 5;

        this.page = 1;
        this.size = 10;
    }


    //评论分页
    //1. 展示第几页的评论
    private Integer commentPage;

    //2. 每页展示多少条父评论
    private Integer commentPageSize;

    //3. 后台查询起始时间
    private Date start_time;

    //4. 后台查询截止时间
    private Date end_time;
}
