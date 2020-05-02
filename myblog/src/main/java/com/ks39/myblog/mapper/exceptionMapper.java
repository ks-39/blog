package com.ks39.myblog.mapper;

import com.ks39.myblog.model.entity.exception;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 14:42 2020/4/21
 */
public interface exceptionMapper {

    void addException(exception exception);

    exception getExceptionsCount();

    List<exception> getExceptions();

    List<exception> searchException(String startTime, String endTime);
}
