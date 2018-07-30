package com.sencerseven.blog.controller;

import com.sencerseven.blog.exception.NotFoundException;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {


    @RequestMapping("/error")
    public void errorAction(Model model) throws NotFoundException{
        throw new NotFoundException();
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
