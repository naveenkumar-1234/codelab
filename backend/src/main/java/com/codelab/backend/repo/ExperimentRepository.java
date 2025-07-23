package com.codelab.backend.repo;

import com.codelab.backend.model.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExperimentRepository  extends JpaRepository<Experiment,Long> {

    Optional<Experiment> findBySubjectIdAndExperimentNo(Long subjectId , Integer experimentNo);

    List<Experiment> findBySubjectId(Long subjectId);


    boolean existsBySubjectIdAndExperimentNo(Long subjectId, Integer experimentNo);

    void deleteBySubjectIdAndExperimentNo(Long subjectId, Integer experimentNo);






}
