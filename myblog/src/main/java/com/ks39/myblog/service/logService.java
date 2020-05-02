package com.ks39.myblog.service;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.entity.log;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 12:59 2020/4/21
 */
public interface logService {

    void addLog(log log);

    PageInfo<log> getLogs(Integer page, Integer size, Integer navigatePages);

    PageInfo<log> searchLog(String startTime, String endTime, Integer page, Integer size, Integer navigatePages);
}
