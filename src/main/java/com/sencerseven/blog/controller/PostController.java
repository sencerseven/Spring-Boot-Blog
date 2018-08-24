package com.sencerseven.blog.controller;

import com.sencerseven.blog.command.CategoryCommand;
import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.converter.PostToPostCommandConverter;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Comment;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.model.JsonResponder;
import com.sencerseven.blog.service.CategoryService;
import com.sencerseven.blog.service.CommentService;
import com.sencerseven.blog.service.PostService;
import org.springframework.data.domain.Page;
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
    PostToPostCommandConverter postToPostCommandConverter;


    public PostController(PostService postService, CategoryService categoryService, CommentService commentService, PostToPostCommandConverter postToPostCommandConverter) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.commentService = commentService;
        this.postToPostCommandConverter = postToPostCommandConverter;
    }

    @GetMapping(value = "{url}")
    public String indexAction(@PathVariable("url") String url, Model model) {

        Post post = postService.getPostByUrlAndActive(url,true);
        PostCommand postCommand = postToPostCommandConverter.convert(post);
        List<Comment> commentList = commentService.findCommentsByPostAndActive(post, true);
        List<PostCommand> findPostList = postService.findPostRand(0, 3, "id", Sort.Direction.DESC, post);
        postService.updateBy(post);

        model.addAttribute("post", postCommand);
        model.addAttribute("commentList", commentList);
        model.addAttribute("commentCommand", new CommentCommand());
        model.addAttribute("suggessPostList", findPostList);


        return "index";
    }

    @RequestMapping
    public String searchAction(@RequestParam(name = "category", required = false) String tmpCategory,
                               @RequestParam(name = "search", required = false) String tmpSearch,
                               @RequestParam(name = "tags", required = false) String tmpTags, Model model) {
        Page<PostCommand> postPage;

        PostCommand postCommand = new PostCommand();
        postCommand.setTags(tmpTags);
        postCommand.setTitle(tmpSearch);
        postCommand.setCategory(new CategoryCommand(tmpCategory,null,null,true,null));

        postPage = postService.findSpecificationPost(0,10,"id",Sort.Direction.DESC,postCommand);



        model.addAttribute("posts", postPage);


        return "index";

    }

    @RequestMapping(method = RequestMethod.POST, value = "/addcomment/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonResponder commentAdd(@Valid @RequestBody CommentCommand commentCommand,
                             @PathVariable("id") Long postID,
                             BindingResult bindingResult) throws Exception {

        JsonResponder jsonResponder = new JsonResponder();

        if (bindingResult.hasErrors()) {
            jsonResponder.setStatus("fail");
            return jsonResponder;
        }


        Post post = postService.findByID(postID);
        if (post != null) {
            CommentCommand responseCommand = commentService.saveCommentCommand(commentCommand, post);
            if (responseCommand != null)
                jsonResponder.setStatus("Success");
            jsonResponder.setObject(responseCommand);
            return jsonResponder;
        }

        return jsonResponder;

    }
}
