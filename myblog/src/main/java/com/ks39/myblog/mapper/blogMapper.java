package com.ks39.myblog.mapper;

import com.ks39.myblog.model.entity.blog;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 18:55 2020/4/14
 */
public interface blogMapper {

    //1. getRecentPostBlog(公共资源)
    List<blog> getRecentPostBlog();

    //2. getBlogs(获取index分页下博客列表)
    List<blog> getBlogs();

    //3. getBlogsByTagId(获取tagId分页下博客列表)
    List<blog> getBlogsByTagId(Long tagId);

    //4. getBlogsByCategoryId(获取categoryId分页下博客列表)
    List<blog> getBlogsByCategoryId(Long categoryId);

    //5. getBlogsBySearch(获取符合search条件分页下博客列表)
    List<blog> getBlogsBySearch(String search);

    //6. getBlogById(获取博客内容)
    List<blog> getBlogById(Long blogId);

    //7. 增加博客浏览量
    void increaseView(Long blogId);



    //后台管理
    //1. 获取博客数量
    blog getBlogsNumber();

    //2. 删除博客
    void deleteBlog(Long blog_id);

    //3. 添加博客
    void postBlog(blog blog);

    //4. 更新博客
    void updateBlog(blog blog);

    //5. 获取所有博客
    List<blog> getAllBlogs();

    //6. 获取
    List<blog> getBlogByBlogId(Long blog_id);
}
