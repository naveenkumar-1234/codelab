package com.codelab.backend.controller;

import com.codelab.backend.model.Experiment;
import com.codelab.backend.response.ApiResponse;
import com.codelab.backend.service.ExperimentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/experiment")
public class ExperimentController {


    private final ExperimentService experimentService;

    public ExperimentController(ExperimentService experimentService){
        this.experimentService = experimentService;
    }

    @PostMapping("/{subjectId}/add")
    public ResponseEntity<?> addExperiment(@PathVariable Long subjectId ,@RequestBody Experiment experiment){

        try{
            return ResponseEntity.status(201).body(
                    experimentService.createExperiment(subjectId , experiment)
            );
        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                    "ss"

                            );
        }
    }

    @GetMapping("/{subjectId}/getAllExperiments")
    public ResponseEntity<?> getAllExp(@PathVariable Long subjectId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(experimentService.getAllExperimentsBySubjectId(subjectId));
    }

    @DeleteMapping("/{subjectId}/{experimentNo}")
    public ResponseEntity<?> deleteExperiment(
            @PathVariable Long subjectId ,
            @PathVariable Integer experimentNo) {

        try {
            experimentService.deleteExperiment(subjectId, experimentNo);
            return
                    ResponseEntity.status(200)
                            .body(
                                    "kk"
                            );

        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                    "ll"
            );
        }


    }

}
