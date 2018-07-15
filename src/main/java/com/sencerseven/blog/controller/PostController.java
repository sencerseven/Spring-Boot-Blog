package com.sencerseven.blog.controller;

import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "{url}")
    public String indexAction(@PathVariable("url") String url,Model model){


        Post post = postService.getPostByUrl(url);

        model.addAttribute("post",post);


        return "index";
    }
}
