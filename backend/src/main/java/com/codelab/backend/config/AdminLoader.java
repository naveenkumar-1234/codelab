package com.codelab.backend.config;

import com.codelab.backend.enums.UserRole;
import com.codelab.backend.model.Admin;
import com.codelab.backend.model.User;
import com.codelab.backend.repo.AdminRepository;
import com.codelab.backend.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        String email = "admin@gmail.com";
        if(userRepository.findByEmail(email).isEmpty()){
            User adminUser = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode("pass"))
                    .role(UserRole.ADMIN)
                    .build();
            adminUser = userRepository.save(adminUser);

//            User managedUser = userRepository.getReferenceById(adminUser.getId());

            Admin admin = Admin.builder()
                    .username("admin 1")
                    .user(adminUser)
                    .build();
            adminRepository.save(admin);

        }

    }
}
