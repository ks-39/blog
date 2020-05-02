package com.ks39.myblog.service;

import com.github.pagehelper.PageInfo;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.model.FileInfo;

import java.io.FileInputStream;
import java.util.List;

public interface qiniuService {
    /**
     * 上传文件并且返回文件地址
     * @param fileInputStream
     * @param key
     * @return
     * @throws QiniuException
     */
    String uploadFile(FileInputStream fileInputStream, String key) throws QiniuException;

    /**
     * 获取文件列表
     * @return
     */
    List<FileInfo[]> getFileList();
}
