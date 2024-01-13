package com.testmarkers.markers.service;

import com.testmarkers.markers.controllers.dto.StudentDTO;
import com.testmarkers.markers.entity.Student;
import com.testmarkers.markers.repository.StudentCrudRepository;
import com.testmarkers.markers.service.interfaces.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImp implements StudentService {
    private final StudentCrudRepository studentCrudRepository;

    public StudentServiceImp(StudentCrudRepository studentCrudRepository) {
        this.studentCrudRepository = studentCrudRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Student> getAll() {
        return this.studentCrudRepository.findAll();
    }

    @Override
    @Transactional
    public Student save(StudentDTO studentDTO) {
        if(this.studentCrudRepository.existsByFirstNameAndLastNameAndEmail(studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getEmail())) {
            log.info("exist student: {}", studentDTO);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error exist student");
        } else {
            try {
                return this.studentCrudRepository.save(StudentDTO.convertToStudent(studentDTO));
            } catch (Exception e) {
                log.info("Error save", e);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error save student");
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long studentId) {
        try {
            this.studentCrudRepository.deleteById(studentId);

        } catch (Exception e) {
            log.info("Error delete", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error delete student");
        }

    }
}
