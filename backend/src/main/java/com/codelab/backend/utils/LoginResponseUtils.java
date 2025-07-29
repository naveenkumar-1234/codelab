package com.codelab.backend.utils;

import com.codelab.backend.dto.user.*;
import com.codelab.backend.model.*;
import com.codelab.backend.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginResponseUtils {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;

    private StudentResponseDto mapToStudentResponse(User user) {
        Student student = studentRepository.findById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Student not found"));

//        List<String> subjects = teacher.getSubjects().stream()
//                .map(Subject::getSubjectName)
//                .collect(Collectors.toList());
        return StudentResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .username(student.getUsername())
                .sprNumber(student.getSprNumber())
                .department(student.getDepartment())
                .academicYear(student.getAcademicYear())
//                .subject(student.getSubject() != null ?
//                        student.getSubject().getSubjectName() : null)
                .build();
    }

    private TeacherResponseDto mapToTeacherResponse(User user) {
        Teacher teacher = teacherRepository.findById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Teacher not found"));

//        List<String> subjects = teacher.getSubjects().stream()
//                .map(Subject::getSubjectName)
//                .collect(Collectors.toList());

        return TeacherResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .username(teacher.getUsername())
                .department(teacher.getDepartment())
//                .subjects(subjects)
                .build();
    }

    private AdminResponseDto mapToAdminResponse(User user) {
        Admin admin = adminRepository.findById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));


        return AdminResponseDto.builder()
                .email(user.getEmail())
                .role(user.getRole().name())
                .username(admin.getUsername())
                .build();
    }

    public UserResponseDto getUserResponseDto(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        System.out.println(user.getRole());
        log.info("{}",user.getRole());
        log.info("{}",user.getEmail());
        return switch (user.getRole().toString()) {

            case "STUDENT" -> mapToStudentResponse(user);
            case "TEACHER" -> mapToTeacherResponse(user);
            case "ADMIN" -> mapToAdminResponse(user);
            default -> throw new IllegalArgumentException("Unknown user role");
        };
    }
}