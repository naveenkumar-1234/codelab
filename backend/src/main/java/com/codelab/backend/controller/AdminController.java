package com.codelab.backend.controller;

import com.codelab.backend.dto.register.StudentRegisterDTO;
import com.codelab.backend.dto.register.TeacherRegisterDTO;
import com.codelab.backend.model.Student;
import com.codelab.backend.model.Teacher;
import com.codelab.backend.response.ApiResponse;
import com.codelab.backend.response.ResponseBuilder;
import com.codelab.backend.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.path}/admin")
@PreAuthorize("ROLE_ADMIN")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/teacher/register")
    public ResponseEntity<ApiResponse<Teacher>> registerTeacher(@Valid @RequestBody TeacherRegisterDTO teacherRegisterDTO){
        return ResponseBuilder.created(
                adminService.registerTeacher(teacherRegisterDTO),
                "Teacher created successfully"

        );

    }
    @PostMapping("/student/register")
    public ResponseEntity<ApiResponse<Student>> registerStudent(@Valid @RequestBody StudentRegisterDTO studentRegisterDTO){
        return ResponseBuilder.created(
                adminService.registerStudent(studentRegisterDTO),
                "Student created successfully"
        );

    }



}
