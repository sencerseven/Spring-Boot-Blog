package com.sencerseven.blog.controller;

import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    PostService postService;

    public IndexController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = {"", "/"})
    public String indexAction(Model model) {

       Page<Post> postPage = postService.findPostsBy(PageRequest.of(0, 10));


       model.addAttribute("posts",postPage);

        return "index";
    }
}
