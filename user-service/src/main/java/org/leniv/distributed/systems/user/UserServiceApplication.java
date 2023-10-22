package org.leniv.distributed.systems.user;

import org.leniv.distributed.systems.user.entity.User;
import org.leniv.distributed.systems.user.entity.User.Role;
import org.leniv.distributed.systems.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner dataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            User customer = User.builder()
                    .username("customer")
                    .password(passwordEncoder.encode("customer"))
                    .build();
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.saveAll(List.of(customer, admin));
        };
    }
}
