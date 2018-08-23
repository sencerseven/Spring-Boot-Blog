package com.sencerseven.blog.controller.admin;

import com.sencerseven.blog.command.SettingsCommand;
import com.sencerseven.blog.service.SettingsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminSettingsController {

    SettingsService settingsService;

    public AdminSettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @RequestMapping("settings")
    public String indexAction(Model model){
        SettingsCommand settingsCommand = settingsService.findById(1);

        model.addAttribute("settingsCommand",settingsCommand);

        return "admin";

    }

    @PostMapping("settings")
    public String indexAction(SettingsCommand settingsCommand, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "admin";
        }

        settingsService.saveCommand(settingsCommand);

        return "redirect:/admin/settings";
    }

}
