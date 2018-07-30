package com.sencerseven.blog.controller.admin;

import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.command.UsersCommand;
import com.sencerseven.blog.converter.PostToPostCommandConverter;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.domain.Users;
import com.sencerseven.blog.service.CategoryService;
import com.sencerseven.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminPostController {

    PostService postService;
    CategoryService categoryService;
    PostToPostCommandConverter postToPostCommandConverter;


    public AdminPostController(PostService postService, CategoryService categoryService, PostToPostCommandConverter postToPostCommandConverter) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.postToPostCommandConverter = postToPostCommandConverter;
    }

    @RequestMapping("post")
    public String indexAction(Model model){


        Page<PostCommand> postList = postService.findPostsBy(0,100,"id",Sort.Direction.DESC);

        model.addAttribute("posts",postList);


        return "admin";
    }

    @GetMapping("post/add")
    public String addAction(Model model){

        PostCommand postCommand = new PostCommand();
        Set<Category> category = categoryService.getCategories();
        model.addAttribute("postCommand",postCommand);
        model.addAttribute("categories",category);

        return "admin";

    }


    @PostMapping("post/add")
    public String addAction(PostCommand postCommand, @AuthenticationPrincipal UsersCommand usersCommand, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "admin";
        }

        postService.savePostsCommand(postCommand,usersCommand);
        return "redirect:/admin/post";

    }

    @GetMapping("post/edit/{id}")
    public String editAction(@PathVariable("id")Long id,Model model){

        Post post = postService.findByID(id);

        PostCommand postCommand = postToPostCommandConverter.convert(post);
        Set<Category> category = categoryService.getCategories();

        model.addAttribute("postCommand",postCommand);
        model.addAttribute("categories",category);



        return "admin";
    }
}
