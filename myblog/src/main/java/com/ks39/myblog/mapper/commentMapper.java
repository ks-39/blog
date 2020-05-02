package com.ks39.myblog.mapper;

import com.ks39.myblog.model.entity.comment;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 19:33 2020/4/16
 */
public interface commentMapper {

    //1. getCommentsByBlogId(获取博客内容页的评论)
    List<comment> getCommentsByBlogId(Long blogId);

    //2. addComment/reply(添加评论/回复)
    void addComment(comment comment);

    // getSubComments（工具sql，查询子评论如何封装到comment实体类的subComments中）
    List<comment> getSubCommentsByParentId(Long parent_comment_id);


    //后台管理
    //1. 统计评论数量
    comment getCommentsNumber();

    //2. 评论列表
    List<comment> getComments();

    //3. 修改评论状态
    void editComment(comment comment);

    //4. 搜索评论
    List<comment> searchComment(String startTime, String endTime);
}
