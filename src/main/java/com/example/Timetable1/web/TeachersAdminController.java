package com.example.Timetable1.web;

import com.example.Timetable1.TeachersAdmin;
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
@RequestMapping("/Teachers_admin")
public class TeachersAdminController {
    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("Teachers_admin", new TeachersAdmin());
        return "Teachers_admin";
    }
    @PostMapping
    public String processDesign(@Valid @ModelAttribute("Teachers_admin") TeachersAdmin Teachers_admin, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "Teachers_admin";
        }
        return "redirect:/orders/current/orderList";
    }

//    @GetMapping("/design3")
//    public String showUpdateForm(Model model) {
//        model.addAttribute("design1", new TeachersAdmin());
//        return "design";
//    }
//    @PostMapping("/design3")
//    public String processUpdate(@Valid @ModelAttribute("design1") TeachersAdmin design, Errors errors, Model model) {
//        if (errors.hasErrors()) {
//            return "design";
//        }
//        return "redirect:/orders/update/{id}(id=${1})";
//    }

}
