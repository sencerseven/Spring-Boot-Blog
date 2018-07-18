package com.sencerseven.blog.advice;

import com.sencerseven.blog.exception.NotFoundCategoryException;
import com.sencerseven.blog.exception.NotFoundSearchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NotFoundSearchException.class)
    public ModelAndView NotFoundSearchException(HttpServletRequest req, Exception ex){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("ex",ex);
        modelAndView.addObject("type","notfoundsearch");
        return modelAndView;
    }

    @ExceptionHandler(NotFoundCategoryException.class)
    public ModelAndView NotFoundCategoryException(){
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("type","categorynotfoundsearch");

        return modelAndView;
    }
}
