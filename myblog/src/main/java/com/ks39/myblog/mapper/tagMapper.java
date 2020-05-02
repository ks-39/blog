package com.ks39.myblog.mapper;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.entity.tag;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 13:26 2020/4/14
 */
public interface tagMapper {

    //1. getTags(公共资源)
    List<tag> getTags();

    //2. getTagByTagId(回显tagId分页博客列表的标签标头)
    tag getTagByTagId(Long tagId);

    //3. getTagListByBlogId(回显博客内容页的tagList)
    List<tag> getTagListByBlogId(Long blog_id);


    //后台管理
    //1. 统计标签数量
    tag getTagsNumber();

    //2. 添加标签
    void addTag(tag tag);

    //3. 删除标签
    void deleteTag(Long tag_id);

    //4. 修改标签
    void editTag(tag tag);

    //5. 搜素标签
    List<tag> searchTag(String search);

    //6. 先存入
    void saveList(List<tag> insertList);

    //7. 再获取tagId
    List<Integer> getTagsId(tag[] tags);

    //8. 统计所有博客
    List<tag> getAllTags();
}
