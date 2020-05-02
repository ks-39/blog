package com.ks39.myblog.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.entity.comment;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 19:33 2020/4/16
 */
public interface commentService {

    //1. getCommentsByBlogId
    List<comment> getCommentsByBlogId(Long blogId);

    //2. addComment/reply
    void addComment(comment comment);

    //3. getComments
    PageInfo<comment> getCommentsByBlogId(Long blog_id, Integer blogPage, Integer blogPageSize, Integer navigatePages);


    //后台管理
    //1. 统计评论数量
    comment getCommentsNumber();

    //2. 评论列表
    PageInfo<comment> getComments(Integer page, Integer size, Integer navigatePages);

    //3. 修改评论状态
    void editComment(comment comment);

    //4. 搜索评论
    PageInfo<comment> searchComment(String startTime, String endTime, Integer page, Integer size, Integer navigatePages);
}
