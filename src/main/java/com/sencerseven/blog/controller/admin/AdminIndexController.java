package com.sencerseven.blog.controller.admin;

import com.sencerseven.blog.service.CategoryService;
import com.sencerseven.blog.service.CommentService;
import com.sencerseven.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    CategoryService categoryService;
    PostService postService;
    CommentService commentService;

    public AdminIndexController(CategoryService categoryService, PostService postService, CommentService commentService) {
        this.categoryService = categoryService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @RequestMapping("")
    public String indexAction(Model model){

        Long categoryCount = categoryService.countByActive(true);

        Long postCount = postService.countByActive(true);

        Long commentCount = commentService.countByActiveAndType(true,"post");

        model.addAttribute("categoryCount",categoryCount);
        model.addAttribute("postCount",postCount);
        model.addAttribute("commentCount",commentCount);

        return "admin";
    }

}
