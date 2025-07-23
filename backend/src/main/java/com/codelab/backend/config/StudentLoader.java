package com.codelab.backend.config;

import com.codelab.backend.enums.UserRole;
import com.codelab.backend.model.Student;
import com.codelab.backend.model.User;
import com.codelab.backend.repo.StudentRepository;
import com.codelab.backend.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class StudentLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        String email = "s@gmail.com";
        if(userRepository.findByEmail(email).isEmpty()){
            User studentUser = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode("pass"))
                    .role(UserRole.STUDENT)
                    .build();
            studentUser = userRepository.save(studentUser);

            Student student = Student.builder()
                    .username("Naveen")
                    .sprNumber(8981)
                    .department("Computer Science")
                    .academicYear(2025)
                    .user(studentUser)
                    .build();
            studentRepository.save(student);
        }
    }
}