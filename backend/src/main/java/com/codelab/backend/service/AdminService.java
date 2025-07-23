package com.codelab.backend.service;

import com.codelab.backend.dto.register.StudentRegisterDTO;
import com.codelab.backend.dto.register.TeacherRegisterDTO;
import com.codelab.backend.exception.CustomException;
import com.codelab.backend.model.Student;
import com.codelab.backend.model.Teacher;
import com.codelab.backend.model.User;
import com.codelab.backend.repo.StudentRepository;
import com.codelab.backend.repo.TeacherRepository;
import com.codelab.backend.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;


    public Teacher registerTeacher(TeacherRegisterDTO teacherDto){
        if(userRepository.findByEmail(teacherDto.getEmail()).isEmpty()){
            User teacherUser = User.builder()
                    .email(teacherDto.getEmail())
                    .role(teacherDto.getUserRole())
                    .password(passwordEncoder.encode(teacherDto.getPassword()))
                    .build();
            teacherUser = userRepository.save(teacherUser);

            Teacher teacher = Teacher.builder()
                    .user(teacherUser)
                    .username(teacherDto.getUsername())
                    .department(teacherDto.getDepartment())
                    .sprNumber(teacherDto.getSprNumber())
                    .build();

            return teacherRepository.save(teacher);
        }
        throw new CustomException("User email already registered");
    }

    public Student registerStudent(StudentRegisterDTO studentRegisterDTO){
        if(userRepository.findByEmail(studentRegisterDTO.getEmail()).isEmpty()){
            User studentUser = User.builder()
                    .email(studentRegisterDTO.getEmail())
                    .role(studentRegisterDTO.getUserRole())
                    .password(passwordEncoder.encode(studentRegisterDTO.getPassword()))
                    .build();
            studentUser = userRepository.save(studentUser);

            Student student = Student.builder()
                    .user(studentUser)
                    .username(studentRegisterDTO.getUsername())
                    .department(studentRegisterDTO.getDepartment())
                    .sprNumber(studentRegisterDTO.getSprNumber())
                    .academicYear(studentRegisterDTO.getAcademicYear())
                    .build();

            return studentRepository.save(student);
        }
        throw new CustomException("User email already registered");
    }
}
