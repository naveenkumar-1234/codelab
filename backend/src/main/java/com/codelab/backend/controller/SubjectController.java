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
    public ResponseEntity<ApiResponse> addSubject(@RequestBody Subject subject){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(
                        "success",
                        subjectService.createSubject(subject)
                )
        );
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse> getSubjects(
            @RequestParam String department,
            @RequestParam Integer year,
            @RequestParam String semesterType){
        return ResponseEntity.status(200).body(
                new ApiResponse(
                        "success",
                        subjectService.getSubjectsByFilters(department,year,semesterType)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getSubject(@PathVariable Long id){
            try{
                return ResponseEntity.status(200).body(
                        new ApiResponse(
                                "success",
                                subjectService.getSubjectById(id)
                        )
                );
            }
            catch (RuntimeException e){
                return ResponseEntity.status(200).body(
                        new ApiResponse(
                                "404",
                                e.getMessage()
                ));
            }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateSubject(@PathVariable Long id, @RequestBody Subject subject){
        try{
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    new ApiResponse(
                            "success",
                            subjectService.updateSubject(id,subject)
                    )
            );

        } catch (RuntimeException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(
                            "success",
                            e.getMessage()
                    )
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
