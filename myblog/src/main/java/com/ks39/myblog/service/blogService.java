package com.ks39.myblog.service;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.entity.blog;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 18:01 2020/4/14
 */
public interface blogService {

    //1. RecentPostBlog(公共资源，获取1页数据，共5篇)
    PageInfo<blog> getRecentPostBlog(int page, int size);

    //2. 获取index分页博客列表（分页，每次获取page（page随前端分页插件随动）页，1页5篇博客，分页插件一次最多显示5页）
    PageInfo<blog> getBlogs(int page, int size,int navigatePage);

    //3. 获取tagId分页博客列表（分页，每次获取page（page随前端分页插件随动）页，1页5篇博客，分页插件一次最多显示5页）
    PageInfo<blog> getBlogsByTagId(Long tagId, Integer page, Integer size, Integer naviagatePages);

    //4. 获取categoryId分页博客列表（分页，每次获取page（page随前端分页插件随动）页，1页5篇博客，分页插件一次最多显示5页）
    PageInfo<blog> getBlogsByCategoryId(Long categoryId, Integer page, Integer size, Integer navigatePages);

    //5. 获取符合search的博客列表（分页，每次获取page（page随前端分页插件随动）页，1页5篇博客，分页插件一次最多显示5页）
    PageInfo<blog> getBlogsBySearch(String search, Integer blogPage, Integer blogPageSize, Integer navigatePages);

    //6. 获取博客内容
    List<blog> getBlogById(Long blogId);

    //7. 增加博客浏览量
    void increaseView(Long blogId);


    // 后台管理
    //1. 获取博客数量
    blog getBlogsNumber();

    //2. 搜索blog,分页为10个数据一页
    PageInfo<blog> searchBlog(String search, Integer page, Integer size, Integer navigatePages);

    //3. 删除博客
    void deleteBlog(Long blog_id);

    //4. 获取所有博客
    PageInfo<blog> getAllBlogs(Integer page, Integer size, Integer navigatePages);

    //5. 获取博客内容
    List<blog> getBlogByBlogId(Long blog_id);
}
