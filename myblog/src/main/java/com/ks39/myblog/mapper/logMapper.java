package com.ks39.myblog.mapper;

import com.ks39.myblog.model.entity.log;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 14:08 2020/4/21
 */
public interface logMapper {
    void addLog(log log);

    List<log> getLogs();

    List<log> searchLog(String startTime, String endTime);
}
