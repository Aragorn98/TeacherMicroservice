package com.example.Timetable1.security;

import com.example.Timetable1.Teacher;
import com.example.Timetable1.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TeacherRepositoryTeacherDetailsService implements UserDetailsService {
    private TeacherRepository teacherRepo;

    @Autowired
    public TeacherRepositoryTeacherDetailsService(TeacherRepository teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Teacher teacher = teacherRepo.findByUsername(username);
        if (teacher != null) {
            return teacher;
        }
        throw new UsernameNotFoundException(
                "Teacher '" + username + "' not found");
    }
}
