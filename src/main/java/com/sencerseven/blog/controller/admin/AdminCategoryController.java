package com.sencerseven.blog.controller.admin;

import com.sencerseven.blog.command.CategoryCommand;
import com.sencerseven.blog.converter.CategoryToCategoryCommandConverter;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("admin")
public class AdminCategoryController {

    CategoryService categoryService;
    CategoryToCategoryCommandConverter categoryToCategoryCommandConverter;

    public AdminCategoryController(CategoryService categoryService, CategoryToCategoryCommandConverter categoryToCategoryCommandConverter) {
        this.categoryService = categoryService;
        this.categoryToCategoryCommandConverter = categoryToCategoryCommandConverter;
    }

    @GetMapping(value = "category")
    public String indexAction(Model model){

        Set<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);

        return "admin";
    }

    @GetMapping(value = "category/add")
    public String addCategoryAction(Model model) {

        Set<Category> categoryList = categoryService.getCategories();
        CategoryCommand categoryCommand = new CategoryCommand();
        model.addAttribute("categories", categoryList);
        model.addAttribute("categoryCommand", categoryCommand);

        return "admin";
    }

    @PostMapping(value = "category/add")
    public String addCategoryAction(@Valid CategoryCommand categoryCommand, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "admin";
        }
        categoryService.saveCategoryCommand(categoryCommand);

        return "redirect:/admin/category";
    }

    @GetMapping(value = "category/delete/{id}")
    public String deleteCategory(@PathVariable Long id) throws Exception {

        categoryService.deleteCategory(id);


        return "redirect:/admin/category";
    }


    @GetMapping(value = "/category/edit/{id}")
    public String editCategoryAction(Model model, @PathVariable Long id) {

        Category category = categoryService.getById(id);

        CategoryCommand categoryCommand = categoryToCategoryCommandConverter.convert(category);
        Set<Category> categoryList = categoryService.getCategories();

        model.addAttribute("categoryCommand", categoryCommand);
        model.addAttribute("categories", categoryList);

        return "admin";
    }

}
