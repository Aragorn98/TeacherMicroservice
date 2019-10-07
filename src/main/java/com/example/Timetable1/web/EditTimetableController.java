package com.example.Timetable1.web;

import javax.validation.Valid;

import com.example.Timetable1.EditTimetable;
import com.example.Timetable1.repository.EditTimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
//end::baseClass[]
//tag::baseClass[]

import lombok.extern.slf4j.Slf4j;

//@RequestMapping
@Slf4j
@Controller
@RequestMapping("/orders")
public class EditTimetableController {

    @Autowired
    private EditTimetableRepository editTimetableRepository;

    //end::baseClass[]
    //tag::orderForm[]
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("edit", new EditTimetable());
//        model.addAttribute("edits", getAllTeachers());
        return "Edit_admin";
    }
//end::orderForm[]

    //tag::handlePostWithValidation[]
    @PostMapping
    public String processOrder(@ModelAttribute("edit") @Valid EditTimetable editTimetable, Errors errors) {
        if (errors.hasErrors()) {
            return "Edit_admin";
            //return "redirect:/orders/current/orderList";
        }

        log.info("EditTimetable added: " + editTimetable);
        addNewTeacher(editTimetable.getTitle(), editTimetable.getUsername(),
                editTimetable.getPassword(), editTimetable.getRole());
        return "redirect:/orders/current/orderList";
    }
    //end::handlePostWithValidation[]
    // tag::baseClass[]


    @GetMapping("/current/orderList")
    public String orderList(Model model) {
        model.addAttribute("edit", new EditTimetable());
        model.addAttribute("edits", editTimetableRepository.findAll());
        return "Edit_admin";
    }



    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewTeacher (@RequestParam String title, @RequestParam String username, @RequestParam String password, @RequestParam String role) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        EditTimetable n = new EditTimetable();
        n.setTitle(title);
        n.setUsername(username);
        n.setPassword(password);
        n.setRole(role);
        editTimetableRepository.save(n);
        return "redirect:/orders/current";
    }


    @GetMapping(path="/")
    public @ResponseBody Iterable<EditTimetable> getAllTeachers() {
        // This returns a JSON or XML with the users
        return editTimetableRepository.findAll();
    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        EditTimetable editTimetable = editTimetableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("order", editTimetable);
        return "editPage";
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable("id") long id, @Valid EditTimetable editTimetable,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            editTimetable.setId(id);
            return "editPage";
        }
        editTimetableRepository.save(editTimetable);
        model.addAttribute("order", editTimetableRepository.findAll());
        return "redirect:/orders/current/orderList";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") long id, Model model) {

        EditTimetable editTimetable = editTimetableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        editTimetableRepository.delete(editTimetable);
        model.addAttribute("order", editTimetableRepository.findAll());
        return "redirect:/orders/current/orderList";
    }

}
//end::baseClass[]
