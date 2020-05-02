package com.ks39.myblog.service;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.entity.view;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 11:36 2020/4/21
 */
public interface viewService {

    void addView(String ip,String browser,String system);

    view getViewsCount();

    PageInfo<view> getViews(Integer page,Integer size,Integer navigatePage);

    PageInfo<view> searchView(String startTime, String endTime, Integer page, Integer size, Integer navigatePages);
}
