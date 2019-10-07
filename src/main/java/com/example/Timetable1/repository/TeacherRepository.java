package com.example.Timetable1.repository;

import com.example.Timetable1.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher findByUsername(String username);
}
