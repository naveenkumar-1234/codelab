package com.codelab.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Subject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Subject Name is required")
    private String subjectName;

    @NotBlank(message = "Semester type is required")
    private String semesterType;

    @NotNull(message = "Academic Year is required")
    private Integer academicYear;

    @NotBlank(message = "Department is required")
    private String department;

    @Column(unique = true)
    private String subjectIdentifier;

    @NotBlank(message = "Subject Code is required")
    private String subjectCode;

    @Column(name = "teacher_id")
//    @NotBlank(message = "Teacher Id is required")
    private Long teacherId;


    @PrePersist
    private void generateIdentifier(){
        this.subjectIdentifier =  String.format("%s-%s-%d-%s",
                department,
                subjectCode,
                academicYear,
                semesterType);
    }


}

