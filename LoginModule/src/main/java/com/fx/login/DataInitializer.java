package com.fx.login;

import com.fx.login.model.User;
import com.fx.login.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initAdmin(UserRepo userRepo) {
        return args -> {
            if (userRepo.findByEmail("admin").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin");
                admin.setPassword("admin");
                admin.setRole(User.Role.Admin);

                userRepo.save(admin);
                System.out.println("Admin user created.");
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }
}
