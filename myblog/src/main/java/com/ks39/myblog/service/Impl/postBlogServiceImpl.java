package com.ks39.myblog.service.Impl;

import com.ks39.myblog.mapper.blogMapper;
import com.ks39.myblog.mapper.categoryBlogMapper;
import com.ks39.myblog.mapper.tagBlogMapper;
import com.ks39.myblog.model.dto.postBlogDto;
import com.ks39.myblog.model.entity.blog;
import com.ks39.myblog.model.entity.category;
import com.ks39.myblog.model.entity.tag;
import com.ks39.myblog.service.postBlogService;
import com.ks39.myblog.service.tagService;
import com.ks39.myblog.util.TagsConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 11:47 2020/4/26
 */
@Service
public class postBlogServiceImpl implements postBlogService {

    @Autowired
    private blogMapper blogMapper;

    @Autowired
    private categoryBlogMapper categoryBlogMapper;

    @Autowired
    private tagBlogMapper tagBlogMapper;

    @Autowired
    private tagService tagService;

    @Override
    public void postBlog(postBlogDto postBlogDto) {

        //创建blog对象
        blog blog = new blog();
        //深拷贝，进行对象之间属性拷贝
        BeanUtils.copyProperties(postBlogDto,blog);
        //创建category对象
        category category = new category();
        //封装categoryId属性
        category.setCategory_id(postBlogDto.getCategoryId());

        //将tagList解析
        tag[] tags = TagsConverter.parse(postBlogDto.getTags());

        if(blog.getBlog_id() == null){
            //1. 新建博客
            if(tags != null){
                //2. 获取tagId,因为使用了TagsConverter进行解析，得到的是String字符串，需要将tag重新存入再获取其Id
                tagService.saveTags(tags);
                List<Integer> tagsId = tagService.getTagsId(tags);

                //3. 先添加文章后设置关联表
                blogMapper.postBlog(blog);
                tagBlogMapper.addTagBlog(blog.getBlog_id(),tagsId);
                categoryBlogMapper.addCategoryBlog(blog.getBlog_id(),category.getCategory_id());

            }else {
                //如果tag为空
                blogMapper.postBlog(blog);
            }

        }else {

            //更新博客
            //1. 先根据博客Id查询tag_blog表的tagList
            List<Integer> tagIdList = tagBlogMapper.getTagIdList(blog.getBlog_id());
            if(tagIdList != null && !tagIdList.isEmpty()){
                //先删除tag_blog表中相关数据
                tagBlogMapper.deleteTagBlog(blog.getBlog_id(),tagIdList);
            }

            //2. 先根据博客Id查询category_blog表打category
            category category1 = categoryBlogMapper.getCategory(blog.getBlog_id());
            if(category1 != null){
                //先删除category_blog表中相关数据
                categoryBlogMapper.deleteCategoryBlog(blog.getBlog_id(),category1.getCategory_id());
            }

            if(tags != null){
                tagService.saveTags(tags);
                List<Integer> tagsId = tagService.getTagsId(tags);

                //3. 先添加文章后设置关联表
                tagBlogMapper.addTagBlog(blog.getBlog_id(),tagsId);
                categoryBlogMapper.addCategoryBlog(blog.getBlog_id(),category.getCategory_id());
            }
            //最后更新博客
            blogMapper.updateBlog(blog);
        }
    }
}
