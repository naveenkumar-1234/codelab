package com.codelab.backend.controller;

import com.codelab.backend.model.Student;
import com.codelab.backend.response.ApiResponse;
import com.codelab.backend.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
@Slf4j
public class StudentController {

}
