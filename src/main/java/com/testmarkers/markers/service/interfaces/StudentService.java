package com.testmarkers.markers.service.interfaces;

import com.testmarkers.markers.controllers.dto.StudentDTO;
import com.testmarkers.markers.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student save(StudentDTO studentDTO);
    void delete(Long studentId);
}
