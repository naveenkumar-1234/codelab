package com.codelab.backend.service;

import com.codelab.backend.exception.ResourceNotFoundException;
import com.codelab.backend.model.Subject;
import com.codelab.backend.repo.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject getSubjectBySubjectCodeAndAcademicYear(String subjectCode, Long academicYear){
           return subjectRepository.findBySubjectCodeAndAcademicYear(subjectCode,academicYear);
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getSubjectsByFilters(String department, Integer year, String semesterType){
        return subjectRepository.findByDepartmentAndAcademicYearAndSemesterType(department,year,semesterType);
    }

    public Subject getSubjectById(Long id){
        return subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject Not Found"));
    }

    public void delete(Long id){

        subjectRepository.deleteById(id);
    }

    public Subject updateSubject(Long id ,Subject updatedSubject){
        Subject existingSubject = getSubjectById(id);

        existingSubject.setSubjectName(updatedSubject.getSubjectName());
        existingSubject.setSubjectCode(updatedSubject.getSubjectCode());
        existingSubject.setAcademicYear(updatedSubject.getAcademicYear());
        existingSubject.setTeacherId(updatedSubject.getTeacherId());
        existingSubject.setDepartment(updatedSubject.getDepartment());
        existingSubject.setSemesterType(updatedSubject.getSemesterType());
//        existingSubject.setSubjectCode(updatedSubject.getSubjectCode());
//        existingSubject.setSubjectCode(updatedSubject.getSubjectCode());
        return subjectRepository.save(existingSubject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
