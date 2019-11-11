package com.example.Timetable1.security;
import com.example.Timetable1.Teacher;
import com.example.Timetable1.repository.TeacherRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
  
  private TeacherRepository teacherRepo;

  public RegistrationController(
          TeacherRepository teacherRepo) {
    this.teacherRepo = teacherRepo;
//    this.passwordEncoder = passwordEncoder;
  }
  
  @GetMapping
  public String registerForm() {
    return "registration";
  }

  @PostMapping
//  public String processRegistration(RegistrationForm form) {
  public void processRegistration(Teacher teacher) {
    //teacherRepo.save(form.toTeacher(passwordEncoder));
    //return "redirect:/Authorization";
    teacherRepo.save(teacher);
  }

}
