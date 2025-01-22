package com.ogrizkov.bootstrap;

import com.ogrizkov.user.User;
import com.ogrizkov.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    @Bean
    public CommandLineRunner leadData(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User(null, "Lohness", "loh@lake.sc", "1234", false));
            userRepository.save(new User(null, "Norman", "norm@gmail.com", "fd3edgvt", true));
        };
    }
}
