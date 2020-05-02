package com.ks39.myblog.service;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.entity.exception;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 14:32 2020/4/21
 */
public interface exceptionService {
    void addException(exception exception);

    exception getExceptionsCount();

    PageInfo<exception> getExceptions(Integer page, Integer size, Integer navigatePages);

    PageInfo<exception> searchException(String startTime, String endTime, Integer page, Integer size, Integer navigatePages);
}
