package com.sencerseven.blog.controller.admin;


import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.service.CommentService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/mail")
public class AdminMailBoxController {


    CommentService commentService;

    public AdminMailBoxController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = {"", "/"})
    public String indexAction(Model model) {

        List<CommentCommand> commentCommandList = commentService.findCommentsByType(0, 10, Sort.Direction.DESC, "id", "contact");
        Long commentCount = commentService.countAllByReadAndType(false,"contact");

        model.addAttribute("commentCommand", commentCommandList);
        model.addAttribute("commentCount", commentCount);

        return "admin";
    }

    @RequestMapping(value = "detail/{id}")
    public String detailAction(@PathVariable Long id,Model model){


        CommentCommand commentCommand = commentService.findById(id);
        commentCommand.setRead(true);
        commentService.saveCommentCommand(commentCommand,commentCommand.getType());

        model.addAttribute("commentCommand",commentCommand);


        return "admin";

    }
}
