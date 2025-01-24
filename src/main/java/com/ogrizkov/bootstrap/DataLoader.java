package com.ogrizkov.bootstrap;

import com.ogrizkov.movieticket.model.Movie;
import com.ogrizkov.movieticket.model.Theater;
import com.ogrizkov.movieticket.repository.MovieRepository;
import com.ogrizkov.movieticket.repository.TheaterRepository;
import com.ogrizkov.user.User;
import com.ogrizkov.user.UserRepository;
import com.ogrizkov.user.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

//@Component
public class DataLoader {
    @Bean
    public CommandLineRunner leadData(UserRepository userRepository,
                                      TheaterRepository theaterRepository,
                                      MovieRepository movieRepository
    ) {
        return args -> {
            userRepository.save(new User(null, "Lohness", "loh@lake.sc", "1234", UserRole.CUSTOMER));
            userRepository.save(new User(null, "Norman", "norm@gmail.com", "fd3edgvt", UserRole.ADMIN));

            theaterRepository.save(new Theater(null, "Starlight Cinema", 255, 15, 17));
            theaterRepository.save(new Theater(null, "Panorama Hall", 180, 12, 15));
            theaterRepository.save(new Theater(null, "Metropolis Theater", 306, 18, 17));
            theaterRepository.save(new Theater(null, "Royal Screen", 224, 14, 16));
            theaterRepository.save(new Theater(null, "Sunset Boulevard Cinema", 195, 13, 15));
            theaterRepository.save(new Theater(null, "Grand Palace Theater", 360, 20, 18));
            theaterRepository.save(new Theater(null, "Urban Multiplex", 272, 16, 17));
            theaterRepository.save(new Theater(null, "Riverside Cinema", 192, 12, 16));
            theaterRepository.save(new Theater(null, "Downtown Screen", 240, 15, 16));
            theaterRepository.save(new Theater(null, "Skyline Theater", 210, 14, 15));

            movieRepository.save(new Movie(null, "Inception", "Sci-Fi", 148, 8, 2010));
            movieRepository.save(new Movie(null, "The Shawshank Redemption", "Drama", 142, 9, 1994));
            movieRepository.save(new Movie(null, "Interstellar", "Sci-Fi", 169, 8, 2014));
            movieRepository.save(new Movie(null, "Pulp Fiction", "Crime", 154, 8, 1994));
            movieRepository.save(new Movie(null, "The Matrix", "Sci-Fi", 136, 8, 1999));
        };
    }
}
