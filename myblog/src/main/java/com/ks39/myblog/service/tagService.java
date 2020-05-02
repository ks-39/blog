package com.ks39.myblog.service;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.entity.blog;
import com.ks39.myblog.model.entity.tag;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 13:24 2020/4/14
 */
public interface tagService {


    //1. getTags(tag公共资源)
    List<tag> getTags();

    //2. 回显tagId下博客列表的标头
    tag getTagByTagId(Long tagId);

    //3. getTagListByBlogId(回显博客内容页的tagList)
    List<tag> getTagListByBlogId(Long blog_id);


    //后台管理
    //1. 统计标签数量
    tag getTagsNumber();

    //2. 标签列表(分页)
    //统计所有博客
    PageInfo<tag> getAllTags(Integer page, Integer size, Integer navigatePages);

    //3. 添加标签
    void addTag(tag tag);

    //4. 删除标签
    void deleteTag(Long tag_id);

    //5. 修改标签
    void editTag(tag tag);

    //6. 搜索标签
    PageInfo<tag> searchTag(String search, Integer page, Integer size, Integer navigatePages);

    //7. 再次存入tag
    void saveTags(tag[] tags);

    //8. 获取tagId
    List<Integer> getTagsId(tag[] tags);


}
