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


    public IndexController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = {"", "/"})
    public String indexAction(Model model) {

       Page<PostCommand> postPage = postService.findPostsBy(0,10,"id", Sort.Direction.DESC);




       model.addAttribute("posts",postPage);


        return "index";
    }

    @RequestMapping(value = {"/login"})
    public String loginAction(@RequestParam(name = "error",required = false) String error, Model model){
        model.addAttribute("error");

        return ("index");
    }
}
