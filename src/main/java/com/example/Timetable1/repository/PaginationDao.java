package com.example.Timetable1.repository;

import com.example.Timetable1.EditTimetable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PaginationDao extends PagingAndSortingRepository<EditTimetable, Integer> {

}