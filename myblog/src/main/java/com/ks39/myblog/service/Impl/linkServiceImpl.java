package com.ks39.myblog.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ks39.myblog.mapper.linkMapper;
import com.ks39.myblog.model.entity.link;
import com.ks39.myblog.service.linkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 17:39 2020/4/13
 */
@Service
public class linkServiceImpl implements linkService {

    @Autowired
    private linkMapper linkMapper;

    //1. getLinks(公共资源)
    @Override
    public List<link> getLinks() {
        return linkMapper.getLinks();
    }

    //2. 后台link列表（分页）
    @Override
    public PageInfo<link> getLinks(Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<link> links = linkMapper.getLinks();
        return new PageInfo<>(links,navigatePages);
    }

    //3. 后台添加link
    @Override
    public void addLink(link link) {
        linkMapper.addLink(link);
    }

    //4. 后台删除link
    @Override
    public void deleteLink(Long link_id) {
        linkMapper.deleteLink(link_id);
    }

    //5. 后台修改link
    @Override
    public void editLink(link link) {
        linkMapper.editLink(link);
    }

    //6. 搜索link
    @Override
    public PageInfo<link> searchLink(String search, Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<link> links = linkMapper.searchLink(search);
        return new PageInfo<>(links,navigatePages);
    }
}
