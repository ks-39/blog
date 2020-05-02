package com.ks39.myblog.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ks39.myblog.customizeException.CustomizeException;
import com.ks39.myblog.customizeException.CustomizeExceptionCodeEunm;
import com.ks39.myblog.mapper.categoryBlogMapper;
import com.ks39.myblog.mapper.categoryMapper;
import com.ks39.myblog.model.entity.category;
import com.ks39.myblog.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 15:16 2020/4/14
 */
@Service
public class categoryServiceImpl implements categoryService {

    @Autowired
    private categoryMapper categoryMapper;

    //1. getCategories(公共资源，不需要分页)
    @Override
    public List<category> getCategories() {
        return categoryMapper.getCategories();
    }

    //2. getCategoryByCategoryId(category分页博客列表下回显  "分类："  )
    @Override
    public category getCategoryByCategoryId(Long categoryId) {
        category categoryByCategoryId = categoryMapper.getCategoryByCategoryId(categoryId);

        //进行异常处理，如果categoryId无法查询得到，则抛出异常
        if(categoryByCategoryId == null)
            throw new CustomizeException(CustomizeExceptionCodeEunm.CATEGORY_NOT_FOUND);
        return categoryByCategoryId;
    }


    //后台管理
    //1. 统计分类数量
    @Override
    public category getCategoriesNumber() {
        return categoryMapper.getCategoriesNumber();
    }

    //2. 获取分类列表
    //统计所有博客
    @Override
    public PageInfo<category> getAllCategories(Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<category> allCategories = categoryMapper.getAllCategories();
        return new PageInfo<>(allCategories,navigatePages);
    }

    //3. 添加分类
    @Override
    public void addCategory(category category) {
        categoryMapper.addCategory(category);
    }


    @Autowired
    private categoryBlogMapper categoryBlogMapper;
    //4. 删除分类
    @Override
    public void deleteCategory(Long category_id) {
        categoryMapper.deleteCategory(category_id);
//        categoryBlogMapper.deleteByCategoryId(category_id);
    }

    //5. 修改分类
    @Override
    public void editCategory(category category) {
        categoryMapper.editCategory(category);
    }

    //6. 搜索分类
    @Override
    public PageInfo<category> searchCategory(String search, Integer page, Integer size, Integer navigatePages) {
        PageHelper.startPage(page,size);
        List<category> categories = categoryMapper.searchCategory(search);
        return new PageInfo<>(categories,navigatePages);
    }


}
