package com.testmarkers.markers.repository;

import com.testmarkers.markers.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentCrudRepository extends CrudRepository<Student, Long> {
    List<Student> findAll();
    boolean existsByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
}
