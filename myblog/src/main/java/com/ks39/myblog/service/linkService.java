package com.ks39.myblog.service;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.entity.link;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 17:27 2020/4/13
 */
public interface linkService {

    //1. getLinks(links公共资源)
    List<link> getLinks();


    //2. 后台获取link列表（要分页）
    PageInfo<link> getLinks(Integer page,Integer size,Integer navigatePages);

    //3. 添加link
    void addLink(link link);

    //4. 删除link
    void deleteLink(Long link_id);

    //5. 修改link
    void editLink(link link);

    //6. 搜索link(分页)
    PageInfo<link> searchLink(String search, Integer page, Integer size, Integer navigatePages);

}
