package com.testmarkers.markers.controllers.dto;

import com.testmarkers.markers.entity.Student;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class StudentDTO {
    @NotEmpty(message = "email is empty")
    private String firstName;
    @NotEmpty(message = "email is empty")
    private String lastName;
    @Email(message = "email not is valid")
    @NotEmpty(message = "email is empty")
    private String email;

    public static Student convertToStudent(StudentDTO studentDTO) {
        return Student.builder()
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .email(studentDTO.getEmail())
                .build();
    }
}
