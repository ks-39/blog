package com.ks39.myblog.service;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.entity.category;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 15:15 2020/4/14
 */
public interface categoryService {

    //1. getCategories(获取category公共资源)
    List<category> getCategories();

    //2. getCategoryByCategoryId(获取categoryId下的博客列表)
    category getCategoryByCategoryId(Long categoryId);


    //后台管理
    //1. 统计分类数量
    category getCategoriesNumber();

    //2. 后台获取分类列表(分页)
    //统计所有博客
    PageInfo<category> getAllCategories(Integer page, Integer size, Integer navigatePages);

    //3. 添加分类
    void addCategory(category category);

    //4. 删除分类
    void deleteCategory(Long category_id);

    //5. 修改分类
    void editCategory(category category);

    //6. 搜索分类
    PageInfo<category> searchCategory(String search, Integer page, Integer size, Integer navigatePages);


}
