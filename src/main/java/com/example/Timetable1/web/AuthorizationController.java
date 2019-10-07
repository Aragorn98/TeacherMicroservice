package com.example.Timetable1.web;

import com.example.Timetable1.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/Authorization")
public class AuthorizationController {

    @GetMapping
    public String showAuthorizationForm(Model model) {
        model.addAttribute("Authorization", new Authorization());
        return "Authorization";
    }
    @PostMapping
    public String processAuthorization(@Valid @ModelAttribute("Authorization") Authorization Authorization, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "Authorization";
        }
        return "redirect:/Teachers_admin";
    }

}
