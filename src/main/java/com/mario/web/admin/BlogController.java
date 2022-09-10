package com.mario.web.admin;

import com.mario.pojo.Blog;
import com.mario.pojo.Type;
import com.mario.pojo.User;
import com.mario.service.BlogService;
import com.mario.service.TagService;
import com.mario.service.TypeService;
import com.mario.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class BlogController {

    //指定一些url的固定值
    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    //注入Service
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    //返回到列表页面
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        //初始化标签
        model.addAttribute("types",typeService.listType());
        return LIST;
    }

    //查询结果只更新当前部分区域
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        //返回一个html页面的片段
        /*
        * <table th:fragment="blogList" class="ui compact teal table">，返回这个部分更新这个table
        * 拿到这个片段重新渲染
        * */
        return "admin/blogs :: blogList";
    }

    //博客更新页面
    @GetMapping("/blogs/input")
    public String input(Model model) {
        //初始化标签与分类
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("blog", new Blog());
        return INPUT;
    }
    //提交新增的博客 页面
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
//        传入当前的user
//        这里要注意改回来这里会报错，暂时放这不动
        blog.setUser((User) session.getAttribute("user"));

        //得到前端传入的type
        blog.setType(typeService.getType(blog.getType().getId()));

        //得到前端传入的tags
        blog.setTags(tagService.listTag(blog.getTagIds()));

        Blog b;
        if (blog.getId() == null) {
            b =  blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if (b == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }
    //跳转到修改页面
    //转到blog编辑页面，与新增页面共用，修改某些参数就是了
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        //初始化标签与分类，就是下拉框中可以选择的部分
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());


        //再找到当前这篇需要修改的博客内容,为了页面拿到tagids
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;
    }

    //删除博客
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }
}
