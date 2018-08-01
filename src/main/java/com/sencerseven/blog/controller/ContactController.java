package com.sencerseven.blog.controller;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.command.TagCommand;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Parameter;
import com.sencerseven.blog.model.JsonResponder;
import com.sencerseven.blog.service.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;

@Controller
public class ContactController {

    CommentService commentService;
    TagService tagService;
    PostService postService;
    CategoryService categoryService;
    ParameterService parameterService;

    public ContactController(CommentService commentService, TagService tagService, PostService postService, CategoryService categoryService, ParameterService parameterService) {
        this.commentService = commentService;
        this.tagService = tagService;
        this.postService = postService;
        this.categoryService = categoryService;
        this.parameterService = parameterService;
    }

    @RequestMapping("/contact")
    public String indexAction(Model model){

        List<TagCommand> tagCommands = tagService.findAll();
        List<PostCommand> populerList = postService.getPopulerPost(0,3,"view",Sort.Direction.DESC);
        List<Category> categoryList = categoryService.getCategoriesByActive(true);
        List<Parameter> parameterList = parameterService.findParameterByKey("ABOUT");

        model.addAttribute("commentCommand",new CommentCommand());
        model.addAttribute("tagCommands",tagCommands);
        model.addAttribute("populerPost",populerList);
        model.addAttribute("categories",categoryList);
        model.addAttribute("parameterList",parameterList);


        return "index";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/contact/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonResponder commentAdd(@Valid @RequestBody CommentCommand commentCommand, BindingResult result){
        JsonResponder jsonResponder = new JsonResponder();
        if(result.hasErrors()){
            jsonResponder.setStatus("fail");
            return jsonResponder;
        }

       CommentCommand responseComment =  commentService.saveCommentCommand(commentCommand,"contact");

        if(responseComment != null){
            jsonResponder.setStatus("Success");
            jsonResponder.setObject(responseComment);
            return jsonResponder;
        }


        jsonResponder.setStatus("fail");
        return jsonResponder;

    }



}
