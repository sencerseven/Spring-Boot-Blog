package com.sencerseven.blog.controller.admin;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.model.JsonResponder;
import com.sencerseven.blog.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminCommentController {

    CommentService commentService;

    public AdminCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("comments")
    public String indexAction(Model model) {

        model.addAttribute("commentCommand", new CommentCommand());


        return "admin";
    }

    @RequestMapping(method = RequestMethod.POST, value = "comments/fetchresult", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonResponder fetchResult(@RequestBody CommentCommand commentCommand) {

        JsonResponder jsonResponder = new JsonResponder();

        List<CommentCommand> commentCommandList = commentService.findAll(commentCommand);

        if(commentCommandList != null){
            jsonResponder.setStatus("success");
            jsonResponder.setObject(commentCommandList);
        }

        return  jsonResponder;
    }
}
