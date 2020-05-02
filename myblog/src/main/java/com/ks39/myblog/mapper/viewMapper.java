package com.ks39.myblog.mapper;

import com.ks39.myblog.model.entity.view;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 11:37 2020/4/21
 */
public interface viewMapper {
    void addView(String ip,String browser,String system);

    view getViewsCount();

    List<view> getViews();

    List<view> searchView(String startTime, String endTime);
}
