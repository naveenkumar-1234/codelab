package com.codelab.backend.config;

import com.codelab.backend.enums.UserRole;
import com.codelab.backend.model.Teacher;
import com.codelab.backend.model.User;
import com.codelab.backend.repo.TeacherRepository;
import com.codelab.backend.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TeacherLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        String email = "sasikala@gmail.com";
        if(userRepository.findByEmail(email).isEmpty()){
            User staffUser = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode("pass"))
                    .role(UserRole.TEACHER)
                    .build();
            staffUser = userRepository.save(staffUser);

            Teacher teacher = Teacher.builder()
                    .username("Sasikala")
                    .department("Computer Science")
                    .sprNumber(9092)
                    .user(staffUser)
                    .build();
            teacherRepository.save(teacher);
        }
    }
}