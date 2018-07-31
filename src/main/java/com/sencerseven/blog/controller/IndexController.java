package com.sencerseven.blog.controller;

import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.command.TagCommand;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Parameter;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.service.CategoryService;
import com.sencerseven.blog.service.ParameterService;
import com.sencerseven.blog.service.PostService;
import com.sencerseven.blog.service.TagService;
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
    TagService tagService;

    public IndexController(PostService postService, CategoryService categoryService, ParameterService parameterService, TagService tagService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.parameterService = parameterService;
        this.tagService = tagService;
    }


    @RequestMapping(value = {"", "/"})
    public String indexAction(Model model) {

       Page<PostCommand> postPage = postService.findPostsBy(0,10,"id", Sort.Direction.DESC);
       List<Category> categoryList = categoryService.getCategoriesByActive(true);
        List<PostCommand> populerList = postService.getPopulerPost(0,3,"view",Sort.Direction.DESC);
        List<Parameter> parameterList = parameterService.findParameterByKey("ABOUT");
        List<TagCommand> tagCommands = tagService.findAll();



       model.addAttribute("posts",postPage);
       model.addAttribute("categories",categoryList);
        model.addAttribute("parameterList",parameterList);
        model.addAttribute("populerPost",populerList);
        model.addAttribute("tagCommands",tagCommands);

        return "index";
    }

    @RequestMapping(value = {"/login"})
    public String loginAction(@RequestParam(name = "error",required = false) String error, Model model){
        model.addAttribute("error");

        return ("index");
    }
}
