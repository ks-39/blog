package com.ks39.myblog.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ks39.myblog.mapper.logMapper;
import com.ks39.myblog.model.entity.log;
import com.ks39.myblog.service.logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 14:07 2020/4/21
 */
@Service
public class logServiceImpl implements logService {

    @Autowired
    private logMapper logMapper;

    @Override
    public void addLog(log log) {
        logMapper.addLog(log);
    }

    @Override
    public PageInfo<log> getLogs(Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<log> logs = logMapper.getLogs();
        return new PageInfo<>(logs,navigatePages);
    }

    @Override
    public PageInfo<log> searchLog(String startTime, String endTime, Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<log> logs = logMapper.searchLog(startTime, endTime);
        return new PageInfo<>(logs,navigatePages);
    }
}
