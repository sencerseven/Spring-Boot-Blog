package com.sencerseven.blog.controller;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.model.JsonResponder;
import com.sencerseven.blog.service.CommentService;
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

@Controller
public class ContactController {

    CommentService commentService;

    public ContactController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/contact")
    public String indexAction(Model model){

        model.addAttribute("commentCommand",new CommentCommand());


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
