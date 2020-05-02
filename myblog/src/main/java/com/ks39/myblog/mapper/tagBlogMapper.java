package com.ks39.myblog.mapper;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 12:36 2020/4/26
 */
public interface tagBlogMapper {

    void addTagBlog(Long blog_id, List<Integer> tagsId);

    List<Integer> getTagIdList(Long blog_id);

    void deleteTagBlog(Long blog_id, List<Integer> tagIdList);

    void deleteByTagBlogId(Long blog_id);

    void deleteByTagId(Long tag_id);
}
