package com.ks39.myblog.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ks39.myblog.mapper.exceptionMapper;
import com.ks39.myblog.model.entity.exception;
import com.ks39.myblog.service.exceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 14:33 2020/4/21
 */
@Service
public class exceptionServiceImpl implements exceptionService {

    @Autowired
    private exceptionMapper exceptionMapper;

    //1. 添加异常日志
    @Override
    public void addException(exception exception) {
        exceptionMapper.addException(exception);
    }

    //2. 统计异常日志
    @Override
    public exception getExceptionsCount() {
        return exceptionMapper.getExceptionsCount();
    }

    //3. 异常日志列表
    @Override
    public PageInfo<exception> getExceptions(Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<exception> exceptions = exceptionMapper.getExceptions();
        return new PageInfo<>(exceptions,navigatePages);
    }

    //4. 搜索异常日志
    @Override
    public PageInfo<exception> searchException(String startTime, String endTime, Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<exception> exceptions = exceptionMapper.searchException(startTime, endTime);
        return new PageInfo<>(exceptions,navigatePages);
    }
}
