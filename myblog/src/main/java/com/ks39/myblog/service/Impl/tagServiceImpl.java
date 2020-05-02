package com.ks39.myblog.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ks39.myblog.customizeException.CustomizeException;
import com.ks39.myblog.customizeException.CustomizeExceptionCodeEunm;
import com.ks39.myblog.mapper.tagBlogMapper;
import com.ks39.myblog.mapper.tagMapper;
import com.ks39.myblog.model.entity.tag;
import com.ks39.myblog.service.tagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 13:25 2020/4/14
 */
@Service
public class tagServiceImpl implements tagService {

    @Autowired
    private tagMapper tagMapper;

    //1. getTags(公共资源，不需要分页)
    @Override
    public List<tag> getTags() {
        return tagMapper.getTags();
    }

    //2. getTagByTagId(回显tag分页博客列表下的tag标头："标签：")
    @Override
    public tag getTagByTagId(Long tagId) {
        tag tagByTagId = tagMapper.getTagByTagId(tagId);

        //进行异常处理，如果tagId不存在，则抛出异常
        if(tagByTagId == null)
            throw new CustomizeException(CustomizeExceptionCodeEunm.TAG_NOT_FOUND);
        return tagByTagId;
    }

    //3. getTagListByBlogId(回显博客内容页的tagList，根据blogId查询)
    @Override
    public List<tag> getTagListByBlogId(Long blog_id) {
        return tagMapper.getTagListByBlogId(blog_id);
    }


    //后台管理
    //1. 统计标签数量
    @Override
    public tag getTagsNumber() {
        return tagMapper.getTagsNumber();
    }

    //2. 标签列表
    @Override
    public PageInfo<tag> getAllTags(Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<tag> allTags = tagMapper.getAllTags();
        return new PageInfo<>(allTags,navigatePages);
    }

    //3. 添加标签
    @Override
    public void addTag(tag tag) {
        tagMapper.addTag(tag);
    }

    @Autowired
    private tagBlogMapper tagBlogMapper;
    //4. 删除标签
    @Override
    public void deleteTag(Long tag_id) {
        tagMapper.deleteTag(tag_id);
//        tagBlogMapper.deleteByTagId(tag_id);
    }

    //5. 修改标签
    @Override
    public void editTag(tag tag) {
        tagMapper.editTag(tag);
    }

    //6. 搜素标签
    @Override
    public PageInfo<tag> searchTag(String search, Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<tag> tags = tagMapper.searchTag(search);
        return new PageInfo<>(tags,navigatePages);
    }

    //7. 先添加tag
    @Override
    public void saveTags(tag[] tags) {

        //先获取全部的tag信息
        List<tag> allTag = tagMapper.getTags();
        List<tag> insertList = new ArrayList<>() ;

        for (tag tag : tags) {

            boolean flag = true;

            //比对tag
            for (tag tagname : allTag) {
                //如果tagname相同，则不存入list
                if (tag.getTag_name().equals(tagname.getTag_name())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                //如果tagname不同，则加入到list中
                insertList.add(tag);
            }
        }

        System.out.println(insertList);
        //如果list部位空，则执行新增tag
        if(!insertList.isEmpty())
            tagMapper.saveList(insertList);
    }

    //8. 再获取tagId
    @Override
    public List<Integer> getTagsId(tag[] tags) {
        return tagMapper.getTagsId(tags);
    }


}
