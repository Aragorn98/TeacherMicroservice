package com.example.Timetable1.repository;

import com.example.Timetable1.EditTimetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EditTimetableRepository extends JpaRepository<EditTimetable, Long>{

    @Query(
            value = "SELECT * FROM teacher u WHERE (u.name like CONCAT('%',?1,'%') or u.username like CONCAT('%',?1,'%')) " +
                    "and u.title = ?2 and u.role=?3",
            nativeQuery = true)
    ArrayList<EditTimetable> findUserByNameOrUsername(String text, String title, String role);

//    ArrayList<EditTimetable> findAllByNameContainingOrUsernameContainingAndTitleAndRole(String text, String text1, String title, String role);
}