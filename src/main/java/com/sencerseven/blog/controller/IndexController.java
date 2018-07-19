package com.sencerseven.blog.controller;

import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Parameter;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.service.CategoryService;
import com.sencerseven.blog.service.ParameterService;
import com.sencerseven.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    PostService postService;
    CategoryService categoryService;
    ParameterService parameterService;

    public IndexController(PostService postService, CategoryService categoryService, ParameterService parameterService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.parameterService = parameterService;
    }

    @RequestMapping(value = {"", "/"})
    public String indexAction(Model model) {

       Page<Post> postPage = postService.findPostsBy(PageRequest.of(0, 10));
       List<Category> categoryList = categoryService.getCategoriesByActive(1);
        List<Post> populerList = postService.getPopulerPost(0,1,"view",Sort.Direction.DESC);
        List<Parameter> parameterList = parameterService.findParameterByKey("ABOUT");



       model.addAttribute("posts",postPage);
       model.addAttribute("categories",categoryList);
        model.addAttribute("parameterList",parameterList);
        model.addAttribute("populerPost",populerList);

        return "index";
    }

    @RequestMapping(value = {"/login"})
    public String loginAction(@RequestParam(name = "error",required = false) String error, Model model){
        model.addAttribute("error");

        return ("index");
    }
}
