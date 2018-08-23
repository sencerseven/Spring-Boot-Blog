package com.sencerseven.blog.controller.admin;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.domain.Comment;
import com.sencerseven.blog.model.JsonResponder;
import com.sencerseven.blog.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        List<CommentCommand> commentCommandList = commentService.findAll(commentCommand,"post");

        if(commentCommandList != null && commentCommandList.size() > 0){
            jsonResponder.setStatus("success");
            jsonResponder.setObject(commentCommandList);
        }

        return  jsonResponder;
    }


    @RequestMapping(method = RequestMethod.POST,value = "comments/changestatus/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonResponder changeStatus(@RequestBody CommentCommand commentCommand,@PathVariable("id")Long id){
        JsonResponder jsonResponder = new JsonResponder();

        Comment comment  =commentService.getById(id);
        if(comment != null){
            comment.setActive(!comment.isActive());

            commentService.saveOrUpdate(comment);

            List<CommentCommand> commentCommandList = commentService.findAll(commentCommand,"post");

            jsonResponder.setStatus("success");
            jsonResponder.setObject(commentCommandList);


        }
        return  jsonResponder;

    }
}
