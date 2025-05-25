package com.example.loan.config;

import com.example.loan.user.Role;
import com.example.loan.user.User;
import com.example.loan.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminBootstrapper implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String adminEmail = "admin@example.com";

        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User admin = User.builder()
                    .email(adminEmail)
                    .password(passwordEncoder.encode("admin123")) // You can change the password
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(admin);
            System.out.println("✔ First admin created: " + adminEmail);
        } else {
            System.out.println("ℹ Admin already exists: " + adminEmail);
        }
    }
}

