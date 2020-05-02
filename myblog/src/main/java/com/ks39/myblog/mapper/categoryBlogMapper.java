package com.ks39.myblog.mapper;

import com.ks39.myblog.model.entity.category;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 12:36 2020/4/26
 */
public interface categoryBlogMapper {

    void addCategoryBlog(Long blog_id, Long category_id);

    category getCategory(Long blog_id);


    void deleteCategoryBlog(Long blog_id, Long category_id);

    void deleteByCategoryBlogId(Long blog_id);

    void deleteByCategoryId(Long category_id);
}
