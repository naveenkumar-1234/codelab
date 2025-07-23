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

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/api/v1/student/dummy")
    public ResponseEntity<?> dummyTest(Authentication authentication) {
        log.info("Auth: {}", authentication);
        log.info("Authorities: {}", authentication.getAuthorities());
        return ResponseEntity.ok("Allowed for Admin only");
    }
    @PostMapping("/dummy")
    public String demo(){
        return "only access by admin";
    }
    @GetMapping("/test")
    public String test(){
        return "working";
    }

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(
                        "success",
                        studentService.addStudent(student)
                ));
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllStudents(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(
                        "success",
                        studentService.getAllStudents()
                ));
    }

//    @DeleteMapping("/deleteAllSt")

    @GetMapping("/getStudentByEmail")
    public ResponseEntity<ApiResponse> getStudentByEmail(@RequestBody Map<String,String> request){
//        System.out.println(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(
                        "success",
                        studentService.getStudentByEmail(request.get("email"))
                ));
    }
}
