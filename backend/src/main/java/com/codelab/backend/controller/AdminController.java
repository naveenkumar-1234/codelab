package com.codelab.backend.controller;

import com.codelab.backend.dto.register.StudentRegisterDTO;
import com.codelab.backend.dto.register.TeacherRegisterDTO;
import com.codelab.backend.response.ApiResponse;
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
@RequestMapping("/api/v1/admin")
@PreAuthorize("ROLE_ADMIN")
@RequiredArgsConstructor
public class AdminController {


    private final AdminService adminService;

    @PostMapping("/teacher/register")
    public ResponseEntity<?> registerTeacher(@Valid @RequestBody TeacherRegisterDTO teacherRegisterDTO){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication);


        return ResponseEntity.status(201).body(
                new ApiResponse(
                        "success",
                        adminService.registerTeacher(teacherRegisterDTO)
                )
        );
    }

    @PostMapping("/student/register")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody StudentRegisterDTO studentRegisterDTO){
        return ResponseEntity.status(201).body(
                new ApiResponse(
                        "success",
                        adminService.registerStudent(studentRegisterDTO)
                )
        );
    }



}
