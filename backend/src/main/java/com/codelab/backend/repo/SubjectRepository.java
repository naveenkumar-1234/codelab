package com.codelab.backend.repo;

import com.codelab.backend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject , Long> {

    List<Subject> findByDepartmentAndAcademicYearAndSemesterType(String department ,Integer academicYear , String semesterType);

    Subject findBySubjectCodeAndAcademicYear(String subjectCode , Long AcademicYear);

    

    @Query(
            "SELECT s FROM Subject s WHERE " +
                    "s.department = :dept AND " +
                    "s.academicYear = :year AND " +
                    "s.semesterType = :semType"
    )

    List<Subject> findSubjectsByFilters(
            @Param("dept") String department,
            @Param("year") Integer academicYear,
            @Param("semType") String semesterType);


    boolean existsBySubjectIdentifier(String subjectIdentifier);
}
