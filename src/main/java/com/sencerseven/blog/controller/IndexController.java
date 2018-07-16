package com.sencerseven.blog.controller;

import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.service.CategoryService;
import com.sencerseven.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    PostService postService;
    CategoryService categoryService;

    public IndexController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = {"", "/"})
    public String indexAction(Model model) {

       Page<Post> postPage = postService.findPostsBy(PageRequest.of(0, 10));
       List<Category> categoryList = categoryService.getCategoriesByActive(1);
        List<Post> populerList = postService.getPopulerPost(0,1,"view",Sort.Direction.DESC);

       model.addAttribute("posts",postPage);
       model.addAttribute("categories",categoryList);
        model.addAttribute("populerPost",populerList);

        return "index";
    }
}
