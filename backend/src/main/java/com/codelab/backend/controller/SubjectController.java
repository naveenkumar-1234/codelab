package com.codelab.backend.controller;

import com.codelab.backend.model.Student;
import com.codelab.backend.model.Subject;
import com.codelab.backend.response.ApiResponse;
import com.codelab.backend.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSubject(@RequestBody Subject subject){
        return ResponseEntity.status(HttpStatus.CREATED).body(
               "sd"
        );
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getSubjects(
            @RequestParam String department,
            @RequestParam Integer year,
            @RequestParam String semesterType){
        return ResponseEntity.status(200).body(
               "d"
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubject(@PathVariable Long id){
            try{
                return ResponseEntity.status(200).body(
                        "s"
                );
            }
            catch (RuntimeException e){
                return ResponseEntity.status(200).body(
                "S");
            }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable Long id, @RequestBody Subject subject){
        try{
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body(
                   "s"
            );

        } catch (RuntimeException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "s"
            );
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Subject>> getAllSubjects(){
        return ResponseEntity.status(200).body(subjectService.getAllSubjects());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable Long id) {
        subjectService.delete(id);
    }

}
