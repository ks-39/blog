package com.ks39.myblog.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.dto.ParamDto;
import com.ks39.myblog.model.entity.imageInfo;
import com.ks39.myblog.service.qiniuService;
import com.ks39.myblog.util.qiniuProperties;
import com.qiniu.storage.model.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 22:59 2020/4/21
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class adminImageController {

    @Autowired
    private qiniuService qiniuService;

    @Autowired
    private qiniuProperties qiniuProperties;

    //1. 上传图片(必须返回json给fileinput方法进行判断.....)
    @RequestMapping("/image/upload")
    @ResponseBody
    public Map<String,Object> uploadImage(@RequestParam("images") MultipartFile imageInfo){
        Map<String,Object> result = new HashMap<>();
        //1. 获取上传文件名
        String fileName = imageInfo.getOriginalFilename();
        try {
            InputStream inputStream = imageInfo.getInputStream();
            String path = qiniuService.uploadFile((FileInputStream) inputStream,fileName) ;
            path = "http://"+path ;
            log.info("图片上传地址 :{}",path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //2. 获取图片列表
    @RequestMapping("/image")
    public String imageList(Model model, ParamDto paramDto, HttpServletRequest request){

        List<imageInfo> imageInfoList = new ArrayList<>() ;
        //获取存储空间的文件列表
        List<FileInfo[]> fileList = qiniuService.getFileList();
        //遍历
        for (FileInfo[] fileInfos : fileList) {

            for (FileInfo fileInfo : fileInfos) {
                String key = fileInfo.key ;
                double fileSize = fileInfo.fsize / 1024 ;
                String url = "http://"+qiniuProperties.getDomain()+"/"+key ;

                //填入实体类List
                imageInfo imageInfo = new imageInfo(url,key,fileSize,null) ;
                imageInfoList.add(imageInfo) ;

            }
        }

        //创建Page类
        Page page = new Page(paramDto.getPage(), paramDto.getSize());
        //为Page类中的total属性赋值
        int total = imageInfoList.size();
        page.setTotal(total);
        //计算当前需要显示的数据下标起始值
        int startIndex = (paramDto.getPage() - 1) * paramDto.getSize();
        int endIndex = Math.min(startIndex + paramDto.getSize(),total);
        //从链表中截取需要显示的子链表，并加入到Page
        page.addAll(imageInfoList.subList(startIndex,endIndex));
        //以Page创建PageInfo
        PageInfo pageInfo = new PageInfo<>(page);

        model.addAttribute("imageInfo",pageInfo);

        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));
        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/image";
        }
        return "admin/image";
    }
}
