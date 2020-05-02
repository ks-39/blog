package com.ks39.myblog.controller;

import com.github.pagehelper.PageInfo;
import com.ks39.myblog.model.dto.ParamDto;
import com.ks39.myblog.model.entity.*;
import com.ks39.myblog.service.*;
import com.ks39.myblog.util.MarkdownUtil;
import lombok.extern.slf4j.Slf4j;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: Ks-39
 * @Description: blog页面controller
 * @Date: Create in 16:17 2020/4/12
 */
@Controller
@RequestMapping("/")
@Slf4j
public class blogController {

    @Autowired
    private configService configService;

    @Autowired
    private linkService linkService;

    @Autowired
    private tagService tagService;

    @Autowired
    private categoryService categoryService;

    @Autowired
    private blogService blogService;

    @Autowired
    private commentService commentService;


    //index首页博客列表（需要分页）
    @RequestMapping(path = {"/",""})
    public String index(ParamDto paramDto,Model model, HttpServletRequest request){

        //(ParamDto)封装pagehelper插件的参数
        //如果使用了@Data注解来简化get/set方法，需要在IDEA安装lombok plugin才能调用get和set
        PageInfo<blog> blogs = blogService.getBlogs(paramDto.getBlogPage(),paramDto.getBlogPageSize(),paramDto.getNavigatePages());

        //统一命名为blogList是为了方便分页插件
        model.addAttribute("blogList",blogs);

        //测试是否为pjax请求
        System.out.println(" '/' : X-Pjax "+request.getHeader("X-Pjax"));

        //如果包含 X-PJAX 请求头 (说明是 pjax 请求) 那么只需要返回局部 html 代码，剩下的交给前端 pjax 插件渲染就好，如果不包含则返回完整 html 代码(包含头尾的 html 代码，不包含 X-PJAX 说明是普通请求)
        if(request.getHeader("X-Pjax") != null){
            return "blog/common-template/article-list" ;
        }

        return common(paramDto,model);
    }

    //tag标签博客列表（需要分页）
    @RequestMapping("/tag")
    public String tagPage(ParamDto paramDto,HttpServletRequest request,Model model){

        //先根据tagId查询tag信息（回显标签说明）
        tag tagByTagId = tagService.getTagByTagId(paramDto.getTagId());

        //根据设置好的pagehelper插件参数查询tagId下的博客
        PageInfo<blog> blogsByTagId = blogService.getBlogsByTagId(paramDto.getTagId(), paramDto.getBlogPage(), paramDto.getBlogPageSize(), paramDto.getNavigatePages());

        //添加属性（blogList属性名统一命名为blogList是为了方便设置前端分页插件）
        model.addAttribute("blogList",blogsByTagId);
        model.addAttribute("tag_id",tagByTagId.getTag_id());
        model.addAttribute("tag_name",tagByTagId.getTag_name());

        //测试是否为pjax请求
        System.out.println("X-Pjax "+request.getHeader("X-Pjax"));

        //如果包含 X-PJAX 请求头 (说明是 pjax 请求) 那么只需要返回局部 html 代码，剩下的交给前端 pjax 插件渲染就好，如果不包含则返回完整 html 代码(包含头尾的 html 代码，不包含 X-PJAX 说明是普通请求)
        if(request.getHeader("X-Pjax") != null){
            return "blog/common-template/article-list" ;
        }

        return common(paramDto,model);
    }

    //category分类博客列表（需要分页）
    @RequestMapping("/category")
    public String categoryPage(ParamDto paramDto,HttpServletRequest request,Model model){

        //根据categoryId查询category信息（用于回显在分类标识）
        category categoryByCategoryId = categoryService.getCategoryByCategoryId(paramDto.getCategoryId());

        //根据设置好的pagehelper插件参数查询categoryId下的博客
        PageInfo<blog> blogsByCategoryId = blogService.getBlogsByCategoryId(paramDto.getCategoryId(), paramDto.getBlogPage(), paramDto.getBlogPageSize(), paramDto.getNavigatePages());

        //添加属性（blogList属性名统一命名为blogList是为了方便设置前端分页插件）
        model.addAttribute("blogList",blogsByCategoryId);
        model.addAttribute("category_id",categoryByCategoryId.getCategory_id());
        model.addAttribute("category_name",categoryByCategoryId.getCategory_name());

        //测试是否为pjax请求
        System.out.println("X-Pjax "+request.getHeader("X-Pjax"));

        //如果包含 X-PJAX 请求头 (说明是 pjax 请求) 那么只需要返回局部 html 代码，剩下的交给前端 pjax 插件渲染就好，如果不包含则返回完整 html 代码(包含头尾的 html 代码，不包含 X-PJAX 说明是普通请求)
        if(request.getHeader("X-Pjax") != null){
            return "blog/common-template/article-list" ;
        }
        return common(paramDto,model);
    }

    //search搜索查询博客（需要分页）
    @RequestMapping("/search")
    public String searchPage(ParamDto paramDto,Model model,HttpServletRequest request){

        //根据blog_title是否包含search字符串查询
        PageInfo<blog> blogsBySearch = blogService.getBlogsBySearch(paramDto.getSearch(), paramDto.getBlogPage(), paramDto.getBlogPageSize(), paramDto.getNavigatePages());

        //添加属性（blogList属性名统一命名为blogList是为了方便设置前端分页插件）
        model.addAttribute("blogList",blogsBySearch);

        //测试是否为pjax请求
        System.out.println("X-Pjax "+request.getHeader("X-Pjax"));

        //如果包含 X-PJAX 请求头 (说明是 pjax 请求) 那么只需要返回局部 html 代码，剩下的交给前端 pjax 插件渲染就好，如果不包含则返回完整 html 代码(包含头尾的 html 代码，不包含 X-PJAX 说明是普通请求)
        if(request.getHeader("X-Pjax") != null){
            return "blog/common-template/article-list" ;
        }

        return common(paramDto,model);
    }

    //blogPage博客内容页
    @RequestMapping("/blog")
    public String blogPage(ParamDto paramDto,Model model,HttpServletRequest request){

        //1. 根据blogId查询博客
        List<blog> blogById = blogService.getBlogById(paramDto.getBlogId());

        //2. 将查询结果存储到blog实体类
        blog blog = blogById.get(0);

        //3. 将博文内容使用markdownUtil转换
        blog.setBlog_content(MarkdownUtil.markdownToHtml(blog.getBlog_content()));

        //4. 根据blogId增加blog_view
        blogService.increaseView(blog.getBlog_id());

        //5. 根据blogId查询父comment评论（parent_comment_id = -1）
        PageInfo<comment> comments = commentService.getCommentsByBlogId(blog.getBlog_id(), paramDto.getCommentPage(), paramDto.getCommentPageSize(), paramDto.getNavigatePages());

        //6. 根据blogId查询tagList
        List<tag> tagListByBlogId = tagService.getTagListByBlogId(blog.getBlog_id());

        //7. 添加博客内容页(article-detail.html)中所需要的属性
        //category信息(blogService一同查询)：category_id、category_name.
        //tag信息(tagService)：tag_id、tag_name
        //comment信息(commentService)
        model.addAttribute("blog",blog);
        model.addAttribute("tagList",tagListByBlogId);
        model.addAttribute("comments",comments);

        //测试是否为pjax请求
        System.out.println("'blog:':X-Pjax "+request.getHeader("X-Pjax"));

        //如果包含 X-PJAX 请求头 (说明是 pjax 请求) 那么只需要返回局部 html 代码，剩下的交给前端 pjax 插件渲染就好，如果不包含则返回完整 html 代码(包含头尾的 html 代码，不包含 X-PJAX 说明是普通请求)
        //article-detail页面也是pjax请求刷新
        if(request.getHeader("X-Pjax") != null){
            return "blog/common-template/article-detail" ;
        }
        return common(paramDto,model);
    }

    //公用资源（除了article-list.html外的其他html资源中所需要查询的数据在这个controller中获取）
    public String common(ParamDto paramDto,Model model){

        //查询config信息
        List<config> configs = configService.getConfigs();
        model.addAttribute("configs",configs);

        //查询link信息
        List<link> links = linkService.getLinks();
        model.addAttribute("links",links);

        //查询tag信息
        List<tag> tags = tagService.getTags();
        model.addAttribute("tags",tags);

        //查询categories信息
        List<category> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);

        //查询RecentPost信息
        PageInfo<blog> recentPostBlogS = blogService.getRecentPostBlog(paramDto.getBlogPage(),paramDto.getBlogPageSize());
        model.addAttribute("recentPostBlogs",recentPostBlogS);

        //如果能获取到"article"属性(在article()方法中存储了)，则表示进入到博客内容页（）
        //在article-detail页面通过F5刷新后会进入到blog-detail页面
        if(model.getAttribute("blog")!=null){
            return "blog/blog-detail";
        }

        return "blog/index";
    }

    @RequestMapping("/comment/post")
    public String addComment(ParamDto paramDto,comment comment,HttpServletRequest request,Model model){

        //获取并设置ip
        String comment_ip = request.getRemoteAddr();
        comment.setComment_ip(comment_ip);

        //设置comment状态为1（可见）
        comment.setComment_status(1);

        //判断是否为父评论（如果parent_comment_id为''和reply_to为''，则为父评论，将这两个值设为null）
        if("".equals(comment.getParent_comment_id())) {      //如果parent_comment_id为-1，则表明发表父评论，将parent_comment_id设为null
            comment.setParent_comment_id(null);
        }

        if("".equals(comment.getReply_to())){     //如果reply_to为'' ,表明发表父评论，将reply_to设为null
            comment.setReply_to(null); ;
        }

        //添加评论
        commentService.addComment(comment);

        //回调局部刷新数据
        PageInfo<comment> comments = commentService.getCommentsByBlogId(comment.getBlog_id(), paramDto.getCommentPage(), paramDto.getCommentPageSize(), paramDto.getNavigatePages());
        model.addAttribute("comments",comments) ;

        //1. 根据blogId查询博客
        List<blog> blogById = blogService.getBlogById(comment.getBlog_id());
        blog blog = blogById.get(0);

        //回显blog_id
        model.addAttribute("blog",blog);
        return "blog/common-template/article-detail::comment";

    }


    @RequestMapping("/comment")
    public String getComments(@RequestParam("blogId")Long blogId,
                              Model model,ParamDto paramDto){

        //回调局部刷新数据
        PageInfo<comment> comments = commentService.getCommentsByBlogId(blogId, paramDto.getCommentPage(), paramDto.getCommentPageSize(), paramDto.getNavigatePages());
        model.addAttribute("comments",comments) ;

        //1. 根据blogId查询博客
        List<blog> blogById = blogService.getBlogById(blogId);

        blog blog = blogById.get(0);

        //回显blog_id
        model.addAttribute("blog",blog);
        //局部刷新
        return "blog/common-template/article-detail::comment";
    }

}
