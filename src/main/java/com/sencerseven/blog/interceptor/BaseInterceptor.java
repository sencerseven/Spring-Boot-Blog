package com.sencerseven.blog.interceptor;


import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.command.TagCommand;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Parameter;
import com.sencerseven.blog.service.CategoryService;
import com.sencerseven.blog.service.ParameterService;
import com.sencerseven.blog.service.PostService;
import com.sencerseven.blog.service.TagService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
public class BaseInterceptor extends HandlerInterceptorAdapter {

    public static final String controllerDef = "Controller";
    public static final String actionDef = "Action";

    Locale locale = new Locale("en","US");

    PostService postService;
    CategoryService categoryService;
    ParameterService parameterService;
    TagService tagService;

    public BaseInterceptor(PostService postService, CategoryService categoryService, ParameterService parameterService, TagService tagService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.parameterService = parameterService;
        this.tagService = tagService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Object s = request.getAttribute("javax.servlet.error.exception");
        String controllerName ="";
        String actionName ="";
        if(handler instanceof HandlerMethod && modelAndView != null){
            HandlerMethod handlerMethod = (HandlerMethod) handler;


            controllerName = handlerMethod.getBeanType().getSimpleName();
            actionName =handlerMethod.getMethod().getName();
            if(controllerName.substring(controllerName.length() - controllerDef.length()).equals(controllerDef)){
                controllerName = controllerName.replace(controllerDef,"").toLowerCase(locale);
            }else{
                controllerName = "";
            }

            if(actionName.substring(actionName.length() - actionDef.length()).equals(actionDef)){
                actionName = actionName.replace(actionDef,"").toLowerCase(locale);
            }else{
                actionName = "";
            }
            String[] path = request.getServletPath().substring(1, request.getServletPath().length()).split("/");
            String viewUrl="";
            String jsUrl ="";
            String cssUrl ="";
            System.out.println(path[0]);
            if(path[0].equals("admin")){
               viewUrl = "admin/page/" +controllerName+"/"+actionName;
               jsUrl = "/js/admin/page/"+controllerName+"/"+actionName+".js";
               cssUrl = "/css/admin/page/"+controllerName+"/"+actionName+".css";
            }else{
                viewUrl = "page/" +controllerName+"/"+actionName;
                jsUrl = "/js/page/"+controllerName+"/"+actionName+".js";
                cssUrl = "/css/page/"+controllerName+"/"+actionName+".css";

                List<Category> categoryList = categoryService.getCategoriesByActive(true);
                List<PostCommand> populerList = postService.getPopulerPost(0,3,"view",Sort.Direction.DESC);
                Map<String,String> parameterList = parameterService.findParameterByTip("SETTINGS");

                List<TagCommand> tagCommands = tagService.findAll();

                modelAndView.addObject("categories",categoryList);
                modelAndView.addObject("parameterList",parameterList);
                modelAndView.addObject("populerPost",populerList);
                modelAndView.addObject("tagCommands",tagCommands);

            }

            modelAndView.addObject("view",viewUrl);
            modelAndView.addObject("js",jsUrl);
            modelAndView.addObject("css",cssUrl);
            modelAndView.addObject("controllerName",controllerName);
            modelAndView.addObject("actionName",actionName);

           /* UsersCommand users = null;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null && authentication.isAuthenticated()){
                Object principal = authentication.getPrincipal();
                if(principal instanceof UsersCommand){
                    users = (UsersCommand) principal;
                }
            }

            modelAndView.addObject("currentUser",users);
            */

        }




    }
}
