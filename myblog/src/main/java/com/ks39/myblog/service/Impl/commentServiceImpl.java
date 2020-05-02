package com.ks39.myblog.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ks39.myblog.mapper.commentMapper;
import com.ks39.myblog.model.entity.comment;
import com.ks39.myblog.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 19:33 2020/4/16
 */
@Service
public class commentServiceImpl implements commentService {

    @Autowired
    private commentMapper commentMapper;

    //1. getCommentsByBlogId(获取博客内容页的comments列表)
    @Override
    public List<comment> getCommentsByBlogId(Long blogId) {
        return commentMapper.getCommentsByBlogId(blogId);
    }

    //2. addComment/Reply(添加评论/回复)
    @Override
    public void addComment(comment comment) {
        commentMapper.addComment(comment);
    }

    //3.
    @Override
    public PageInfo<comment> getCommentsByBlogId(Long blog_id, Integer blogPage, Integer blogPageSize, Integer navigatePages) {
        PageHelper.startPage(blogPage,blogPageSize);
        List<comment> commentsByBlogId = commentMapper.getCommentsByBlogId(blog_id);

        return new PageInfo<>(commentsByBlogId,navigatePages);
    }


    //后台管理
    //1. 统计评论数量
    @Override
    public comment getCommentsNumber() {
        return commentMapper.getCommentsNumber();
    }

    //2. 评论列表
    @Override
    public PageInfo<comment> getComments(Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<comment> comments = commentMapper.getComments();
        return new PageInfo<>(comments,navigatePages);
    }

    //3. 修改评论状态
    @Override
    public void editComment(comment comment) {
        commentMapper.editComment(comment);
    }

    //4. 搜索评论
    @Override
    public PageInfo<comment> searchComment(String startTime, String endTime, Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<comment> comments = commentMapper.searchComment(startTime, endTime);
        return new PageInfo<>(comments,navigatePages);
    }

}
