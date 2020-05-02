package com.ks39.myblog.mapper;

import com.ks39.myblog.model.entity.link;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 17:40 2020/4/13
 */
public interface linkMapper {

    //1. getLinks(公共资源)
    List<link> getLinks();

    //2. 添加link
    void addLink(link link);

    //3. 删除link
    void deleteLink(Long link_id);

    //4. 修改link
    void editLink(link link);

    //5. 搜索link
    List<link> searchLink(String search);
}
