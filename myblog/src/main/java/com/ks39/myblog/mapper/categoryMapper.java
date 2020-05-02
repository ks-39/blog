package com.ks39.myblog.mapper;

import com.ks39.myblog.model.entity.category;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 15:16 2020/4/14
 */
public interface categoryMapper {

    //1. getCategories(公共资源);
    List<category> getCategories();

    //2. getCategoryByCategoryId(回显categoryId分页下分类标头)
    category getCategoryByCategoryId(Long categoryId);



    //后台管理
    //1. 统计分类数量
    category getCategoriesNumber();

    //2. 添加分类
    void addCategory(category category);

    //3. 删除分类
    void deleteCategory(Long category_id);

    //4. 修改分类
    void editCategory(category category);

    //5. 搜索
    List<category> searchCategory(String search);

    //6. 统计所用博客
    List<category> getAllCategories();
}
