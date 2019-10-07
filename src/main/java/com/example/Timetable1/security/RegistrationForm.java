package com.example.Timetable1.security;

import com.example.Timetable1.Teacher;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationForm {
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @NotEmpty(message="Username is required")
  private String username;
  @NotEmpty(message="Password is required")
  private String password;
  @NotEmpty(message="Name is required")
  private String name;
  @NotEmpty(message="Academic title is required")
  private String title;
  @NotEmpty(message="Role is required")
  private String role;

  
  public Teacher toTeacher(PasswordEncoder passwordEncoder) {
    return new Teacher(
        username, passwordEncoder.encode(password), 
        name, title, role);
  }
  
}
