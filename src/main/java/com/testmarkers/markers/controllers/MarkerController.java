package com.testmarkers.markers.controllers;

import com.testmarkers.markers.controllers.dto.StudentDTO;
import com.testmarkers.markers.entity.Student;
import com.testmarkers.markers.service.interfaces.StudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("v1/students")
public class MarkerController {

    private final StudentService studentService;

    public MarkerController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public CollectionModel<EntityModel<Student>> getAll() {
        List<Student> students = this.studentService.getAll();

        List<EntityModel<Student>> studentModels = students.stream()
                .map(student -> EntityModel.of(student,
                        WebMvcLinkBuilder.linkTo(MarkerController.class).slash(student.getId()).withSelfRel()))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Student>> responseModel = CollectionModel.of(studentModels);
        responseModel.add(WebMvcLinkBuilder.linkTo(MarkerController.class).withSelfRel());
        return responseModel;
    }

    @PostMapping
    public ResponseEntity<Student> save(@Valid  @RequestBody  StudentDTO studentDTO) {
        return ResponseEntity.ok(this.studentService.save(studentDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.studentService.delete(id);
    }
}
