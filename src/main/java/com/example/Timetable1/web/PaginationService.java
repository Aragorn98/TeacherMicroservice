package com.example.Timetable1.web;
import com.example.Timetable1.EditTimetable;
import com.example.Timetable1.repository.EditTimetableRepository;
import com.example.Timetable1.repository.PaginationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PaginationService {
    private PaginationDao paginationDao;
    @Autowired
    public PaginationService(PaginationDao paginationDao){
        this.paginationDao = paginationDao;
    }


    public Iterable<EditTimetable> findJsonDataByCondition(String orderBy, String direction, int page, int size) {
        Sort sort = null;
        if (direction.equals("ASC")) {
            sort = Sort.by(Sort.Direction.ASC, orderBy);
        }
        if (direction.equals("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, orderBy);
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<EditTimetable> data = paginationDao.findAll(pageable);
        ArrayList<EditTimetable> list = new ArrayList<>();
        for (Iterator<EditTimetable> it = data.iterator(); it.hasNext(); ) {
            EditTimetable editTimetable = it.next();
            list.add(editTimetable);
        }
        return list;
    }
}