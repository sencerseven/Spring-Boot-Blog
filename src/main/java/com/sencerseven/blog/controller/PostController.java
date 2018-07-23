package com.sencerseven.blog.controller;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.command.CommentUserCommand;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Comment;
import com.sencerseven.blog.domain.Parameter;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.model.JsonResponder;
import com.sencerseven.blog.service.CategoryService;
import com.sencerseven.blog.service.CommentService;
import com.sencerseven.blog.service.ParameterService;
import com.sencerseven.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    PostService postService;
    CategoryService categoryService;
    CommentService commentService;
    ParameterService parameterService;

    public PostController(PostService postService, CategoryService categoryService, CommentService commentService, ParameterService parameterService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.commentService = commentService;
        this.parameterService = parameterService;
    }

    @GetMapping(value = "{url}")
    public String indexAction(@PathVariable("url") String url, Model model) {

        Post post = postService.getPostByUrl(url);
        List<Post> populerList = postService.getPopulerPost(0, 3, "view", Sort.Direction.DESC);
        List<Category> categoryList = categoryService.getCategoriesByActive(true);
        List<Comment> commentList = commentService.findCommentsByPostAndActive(post,1);
        List<Parameter> parameterList = parameterService.findParameterByKey("ABOUT");

        postService.updateBy(post);

        model.addAttribute("post", post);
        model.addAttribute("populerPost", populerList);
        model.addAttribute("categories", categoryList);
        model.addAttribute("commentList",commentList);
        model.addAttribute("parameterList",parameterList);
        model.addAttribute("commentCommand", new CommentCommand());


        return "index";
    }

    @RequestMapping
    public String searchAction(@RequestParam(name="category",required = false)String tmpCategory,
                               @RequestParam(name="search",required = false)String tmpSearch,Model model){
        Page<Post> postPage;

        if(tmpCategory != null && !tmpCategory.equals("")){
            Category category = categoryService.getCategoriesByUrl(tmpCategory);
            postPage = postService.findPostsByCategory(0,10,"id",Sort.Direction.DESC,category);
        }else{
            postPage = postService.findPostByTitleContaining(0,10,"id",Sort.Direction.DESC,tmpSearch);
        }



        List<Category> categoryList = categoryService.getCategoriesByActive(true);
        List<Post> populerList = postService.getPopulerPost(0,1,"view",Sort.Direction.DESC);

        model.addAttribute("posts",postPage);
        model.addAttribute("categories",categoryList);
        model.addAttribute("populerPost",populerList);


        return "index";

    }

    @RequestMapping(method = RequestMethod.POST, value = "/addcomment/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonResponder commentAdd(@Valid @RequestBody CommentCommand commentCommand,
                             @PathVariable("id")Long postID,
                             BindingResult bindingResult) throws Exception {

        JsonResponder jsonResponder = new JsonResponder();

        if(bindingResult.hasErrors()){
            jsonResponder.setStatus("fail");
            return  jsonResponder;
        }


        Post post = postService.findByID(postID);
        if(post != null){
            CommentCommand responseCommand = commentService.saveCommentCommand(commentCommand,post);
            if(responseCommand != null)
                jsonResponder.setStatus("Success");
                jsonResponder.setObject(responseCommand);
                return jsonResponder;
        }

        return jsonResponder;

    }
}
