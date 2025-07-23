package com.codelab.backend.service;

import com.codelab.backend.exception.CustomException;
import com.codelab.backend.exception.ResourceNotFoundException;
import com.codelab.backend.model.Experiment;
import com.codelab.backend.model.Subject;
import com.codelab.backend.repo.ExperimentRepository;
import com.codelab.backend.repo.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExperimentService {

    private final ExperimentRepository experimentRepository;
    private final SubjectRepository subjectRepository;
    public ExperimentService(ExperimentRepository experimentRepository, SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
        this.experimentRepository = experimentRepository;
    }

    public Experiment createExperiment(Long subjectId, Experiment experiment){
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(()-> new ResourceNotFoundException("Subject not found"));
        if(experimentRepository.existsBySubjectIdAndExperimentNo(subjectId,experiment.getExperimentNo())){
            throw new CustomException("Exp no is already there");
        }

        experiment.setSubject(subject);
//        if( testCases != null){
//            for(TestCase testCase : testCases){
//                experiment.getTestCaseList().add(testCase);
//            }
//        }
        return experimentRepository.save(experiment);
    }

    @Transactional(readOnly = true)
    public List<Experiment> getAllExperimentsBySubjectId(Long subjectId){
        if(!subjectRepository.existsById(subjectId))
            throw new ResourceNotFoundException("subject not found");
        return experimentRepository.findBySubjectId(subjectId);
    }


//    public Experiment updateExperiment(Long subjectId , Integer experimentNo ,Experiment experiment){
//        Optional<Experiment> existingExp = experimentRepository.findBySubjectIdAndExperimentNo(subjectId,experimentNo);
//        return  experiment;
//    }

    public Optional<Experiment> getParticularExperiment(Long subjectId, Integer experimentNo){
        if(!subjectRepository.existsById(subjectId))
            throw new ResourceNotFoundException("subject not found");
        if(!experimentRepository.existsBySubjectIdAndExperimentNo(subjectId,experimentNo))
            throw new ResourceNotFoundException("Exp not found in this subject");

        return experimentRepository.findBySubjectIdAndExperimentNo(subjectId,experimentNo);
    }

    public void deleteExperiment(Long subjectId, Integer experimentNo) {
        if(!subjectRepository.existsById(subjectId))
            new ResourceNotFoundException("no subject");
        if(!experimentRepository.existsBySubjectIdAndExperimentNo(subjectId,experimentNo))
            new CustomException("no exp in this sub code");
        experimentRepository.deleteBySubjectIdAndExperimentNo(subjectId,experimentNo);
    }
}
