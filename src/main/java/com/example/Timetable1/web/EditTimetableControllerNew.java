package com.example.Timetable1.web;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import com.example.Timetable1.EditTimetable;
import com.example.Timetable1.Teacher;
import com.example.Timetable1.repository.EditTimetableRepository;
import com.example.Timetable1.repository.PaginationDao;
import com.example.Timetable1.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
//end::baseClass[]
//tag::baseClass[]

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@RestController
@RequestMapping("/teachers")
public class EditTimetableControllerNew {

    private EditTimetableRepository editTimetableRepository;
    private TeacherRepository teacherRepo;
    private PaginationDao paginationDao;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public EditTimetableControllerNew(EditTimetableRepository editTimetableRepository,
                                      TeacherRepository teacherRepo, PaginationDao paginationDao, PasswordEncoder passwordEncoder) {
        this.editTimetableRepository = editTimetableRepository;
        this.teacherRepo = teacherRepo;
        this.paginationDao = paginationDao;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/ps")
    public Iterable<EditTimetable> getAllTeachers(@RequestParam String orderBy, @RequestParam String direction,
                                                  @RequestParam int page, @RequestParam int size) {
        System.out.println("HomeController.getAllTacos");
        PaginationService paginationService = new PaginationService(paginationDao);
        return paginationService.findJsonDataByCondition(orderBy, direction, page, size);


    }

    @GetMapping("/psOrderBy")
    public Iterable<EditTimetable> getAllTeachers(@RequestParam String orderBy) {
        System.out.println("HomeController.getAllTacos");
        PaginationService paginationService = new PaginationService(paginationDao);
        return paginationService.findJsonDataByCondition(orderBy, "ASC",0,5);
    }

    @GetMapping
    public Iterable<EditTimetable> getAllTeachers() {
        System.out.println("HomeController.getAllTacos");
        return editTimetableRepository.findAll();

    }

    @GetMapping("/find")
    public Iterable<EditTimetable> findTeachers(@RequestParam String searchText, @RequestParam String title,
                                                  @RequestParam String role) {
//        return editTimetableRepository.findUserByNameOrUsername(searchText, title, role);
        System.out.println(searchText + title + role);
        return editTimetableRepository.findUserByNameOrUsername(searchText
                ,title,role);
    }



    //Teacher not UserDetails?
    @GetMapping("/user/{username}")
    public UserDetails getUserByUsername(@PathVariable("username") String username) throws UsernameNotFoundException {
        System.out.println("HomeController.getUserByUsername");
        System.out.println("username = " + username);
        System.out.println("userRepository = " + editTimetableRepository.findAll());
        Teacher teacher = teacherRepo.findByUsername(username);
        if (teacher != null) {
            return teacher;
        }
        throw new UsernameNotFoundException(
                "Teacher '" + username + "' not found");
    }

    @GetMapping("/userID/{id}")
    public EditTimetable getUserById(@PathVariable("id") long id) throws UsernameNotFoundException {
        System.out.println("HomeController.getUserByUsername");
        System.out.println("id = " + id);
        System.out.println("userRepository = " + editTimetableRepository.findAll());
        EditTimetable editTimetable = editTimetableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        return editTimetable;
    }

    @GetMapping("/teacherName/{id}")
    public String getTeacherNameById(@PathVariable("id") long id) throws NameNotFoundException {
        System.out.println("argyn is here");
        EditTimetable editTimetable = editTimetableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        System.out.println(editTimetable.getName());
        return editTimetable.getName();
    }


    @PostMapping("/user/register")
    public EditTimetable saveTeacher(@RequestBody @Valid EditTimetable editTimetable) {
        System.out.println("HomeController.saveUser");
        System.out.println("user = " + editTimetable);
        Teacher teacher = editTimetable.toTeacher(passwordEncoder);

        editTimetable.setId(teacher.getId());
        editTimetable.setTitle(teacher.getTitle());
        editTimetable.setUsername(teacher.getUsername());
        editTimetable.setPassword(teacher.getPassword());
        editTimetable.setRole(teacher.getRole());
        editTimetable.setName(teacher.getName());
        editTimetable.setROLE_PREFIX(teacher.getROLE_PREFIX());

        return editTimetableRepository.save(editTimetable);
    }




    @PutMapping("/user/update/{id}")
    public void updateTeacher(@PathVariable long id, @RequestBody EditTimetable editTimetable) {
        if (editTimetable.getId() != id) {
            throw new IllegalStateException("Given ingredient's ID doesn't match the ID in the path.");
        }
        editTimetableRepository.save(editTimetable);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteTeacher(@PathVariable long id) {
        editTimetableRepository.deleteById(id);
    }

}
