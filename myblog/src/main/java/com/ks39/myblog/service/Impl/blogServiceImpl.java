package com.ks39.myblog.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ks39.myblog.customizeException.CustomizeException;
import com.ks39.myblog.customizeException.CustomizeExceptionCodeEunm;
import com.ks39.myblog.mapper.blogMapper;
import com.ks39.myblog.mapper.categoryBlogMapper;
import com.ks39.myblog.mapper.exceptionMapper;
import com.ks39.myblog.mapper.tagBlogMapper;
import com.ks39.myblog.model.entity.blog;
import com.ks39.myblog.model.entity.exception;
import com.ks39.myblog.service.blogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 18:54 2020/4/14
 */
@Service
public class blogServiceImpl implements blogService {

    @Autowired
    private blogMapper blogMapper;

    @Autowired
    private exceptionMapper exceptionMapper;

    //1. getRecentPostBlog(公共资源，需要分页)
    @Override
    public PageInfo<blog> getRecentPostBlog(int page, int size) {
        PageHelper.startPage(page,size);
        List<blog> recentPostBlogs = blogMapper.getRecentPostBlog();
        return new PageInfo<>(recentPostBlogs);
    }

    //2. getBlogs（index获取所有已经发布的博客，需要分页）
    @Override
    public PageInfo<blog> getBlogs(int page, int size,int navigatePage) {
        PageHelper.startPage(page,size);
        List<blog> blogs = blogMapper.getBlogs();
        return new PageInfo<>(blogs,navigatePage);
    }

    //3. getBlogsByTagId（查询tagId下的博客列表，需要分页）
    @Override
    public PageInfo<blog> getBlogsByTagId(Long tagId, Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<blog> blogsByTagId = blogMapper.getBlogsByTagId(tagId);
        return new PageInfo<>(blogsByTagId,navigatePages);
    }

    //4. getBlogsByCategoryId（查询categoryId下的博客列表，需要分页）
    @Override
    public PageInfo<blog> getBlogsByCategoryId(Long categoryId, Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<blog> blogsByCategoryId = blogMapper.getBlogsByCategoryId(categoryId);
        return new PageInfo<>(blogsByCategoryId,navigatePages);
    }

    //5. getBlogsBySearch（查询符合搜索条件的博客列表，需要分页）
    @Override
    public PageInfo<blog> getBlogsBySearch(String search, Integer blogPage, Integer blogPageSize, Integer navigatePages) {
        PageHelper.startPage(blogPage,blogPageSize);
        List<blog> blogsBySearch = blogMapper.getBlogsBySearch(search);

        //判断searchException,不抛出，直接将错误信息存入
        if(blogsBySearch == null || blogsBySearch.isEmpty()){     //处理异常（当bolgId不存在时）
            CustomizeException customizeException = new CustomizeException(CustomizeExceptionCodeEunm.SEARCH_NOT_FOUND) ;
            exception exception = new exception();
            exception.setException_code(customizeException.getCode());
            exception.setException_message(customizeException.getMessage());
            exceptionMapper.addException(exception);
        }
        return new PageInfo<>(blogsBySearch,navigatePages);
    }

    //6. getBlogByBlogId(查询博客内容，需要异常处理)
    @Override
    public List<blog> getBlogById(Long blogId) {
        List<blog> blogById = blogMapper.getBlogById(blogId);
        if(blogById == null || blogById.isEmpty()){     //处理异常（当bolgId不存在时）
            throw new CustomizeException(CustomizeExceptionCodeEunm.ARTICLE_NOT_FOUND) ;
        }
        return blogById;
    }

    //7. 增加博客浏览量
    @Override
    public void increaseView(Long blogId) {
        blogMapper.increaseView(blogId);
    }



    //后台管理
    //1. 获取博客数量
    @Override
    public blog getBlogsNumber() {
        return blogMapper.getBlogsNumber();
    }

    //2. 搜索博客
    @Override
    public PageInfo<blog> searchBlog(String search, Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        //和前端搜索共用一个搜索service方法
        List<blog> blogs = blogMapper.getBlogsBySearch(search);
        return new PageInfo<>(blogs,navigatePages);
    }


    @Autowired
    private tagBlogMapper tagBlogMapper;

    @Autowired
    private categoryBlogMapper categoryBlogMapper;

    @Override
    public void deleteBlog(Long blog_id) {

        blogMapper.deleteBlog(blog_id);
        tagBlogMapper.deleteByTagBlogId(blog_id);
        categoryBlogMapper.deleteByCategoryBlogId(blog_id);

    }

    //获取所有博客
    @Override
    public PageInfo<blog> getAllBlogs(Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<blog> allBlogs = blogMapper.getAllBlogs();
        return new PageInfo<>(allBlogs,navigatePages);
    }

    //获取博客内容
    @Override
    public List<blog> getBlogByBlogId(Long blog_id) {
        return blogMapper.getBlogByBlogId(blog_id);
    }

}
