package com.ks39.myblog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.customizeException.CustomizeException;
import com.ks39.myblog.customizeException.CustomizeExceptionCodeEunm;
import com.ks39.myblog.model.dto.ParamDto;
import com.ks39.myblog.model.dto.postBlogDto;
import com.ks39.myblog.model.entity.blog;
import com.ks39.myblog.model.entity.category;
import com.ks39.myblog.model.entity.tag;
import com.ks39.myblog.service.blogService;
import com.ks39.myblog.service.categoryService;
import com.ks39.myblog.service.postBlogService;
import com.ks39.myblog.service.tagService;
import com.ks39.myblog.util.TagsConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: Ks-39
 * @Description:
 * @Date: Create in 17:35 2020/4/21
 */
@RequestMapping("/admin")
@Controller
@Slf4j
public class adminBlogController {

    @Autowired
    private blogService blogService;

    @Autowired
    private tagService tagService;

    @Autowired
    private categoryService categoryService;

    @Autowired
    private postBlogService postBlogService;

    @RequestMapping("/blog")
    public String blog(HttpServletRequest request, Model model, ParamDto paramDto){

        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));

        //与前端页面搜索使用同一个service方法，但是分页参数不同
        PageInfo<blog> blogs = blogService.getAllBlogs(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("blogs",blogs);

        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/blog" ;
        }
        return "admin/blog";
    }


    @RequestMapping("/blog/search")
    public String searchBlog(ParamDto paramDto,Model model,HttpServletRequest request){
        //1. 搜索blog
        PageInfo<blog> blogs = blogService.searchBlog(paramDto.getSearch(),paramDto.getPage(),paramDto.getSize(),paramDto.getNavigatePages());
        //2. 局部刷新
        model.addAttribute("blogs",blogs);
        //3. pjax请求
        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/blog" ;
        }
        return "admin/blog";
    }


    @RequestMapping("/blog/delete")
    public String deleteBlog(blog blog,Model model,ParamDto paramDto){
        //1. 删除blog
        blogService.deleteBlog(blog.getBlog_id());
        //2. 局部刷新
        PageInfo<blog> blogs = blogService.getAllBlogs(paramDto.getPage(), paramDto.getSize(), paramDto.getNavigatePages());
        model.addAttribute("blogs",blogs);
        return "admin/page-fragment/blog";
    }


    @RequestMapping("/blog/edit")
    public String editBlog(@RequestParam(value = "blog_id",required = false)Long blog_id, Model model, ParamDto paramDto,HttpServletRequest request){

        if(blog_id != null){
            //如果id不为空，则表示编辑已存在博客，回显数据
            //1. 根据blogId查询博客
            List<blog> blogById = blogService.getBlogByBlogId(blog_id);
            blog blog = blogById.get(0) ;

            //6. 根据blogId查询tagList
            List<tag> tagListByBlogId = tagService.getTagListByBlogId(blog.getBlog_id());
            String tags = TagsConverter.parse(tagListByBlogId) ;
            model.addAttribute("tags",tags);
            model.addAttribute("blog",blog);

        }else {
            //如果id为空，表示编辑新博客，回显一个新的article对象
            model.addAttribute("blog",new blog());
            List<tag> AllTags = tagService.getTags();
            String tags = TagsConverter.parse(AllTags) ;
            model.addAttribute("tags",tags);
        }

        List<category> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        System.out.println("'admin:':X-Pjax "+request.getHeader("X-Pjax"));
        if(request.getHeader("X-Pjax") != null){
            return "admin/page-fragment/post-blog" ;
        }
        return "admin/post-blog";
    }

    @RequestMapping("/blog/post")
    public String postBlog(postBlogDto postBlogDto, BindingResult result,HttpServletRequest request,ParamDto paramDto,Model model){

        //判断是否有错误（不符合NotNull注解，非法参数）
        if(result.hasErrors()) {
            //抛出非法参数异常
            throw new CustomizeException(CustomizeExceptionCodeEunm.ILLEGAL_ERROR);
        }

        postBlogService.postBlog(postBlogDto);
        return null;
    }
}
