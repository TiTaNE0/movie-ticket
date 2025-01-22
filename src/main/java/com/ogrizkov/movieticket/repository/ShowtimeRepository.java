package com.ogrizkov.movieticket.repository;

import com.ogrizkov.movieticket.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
//
//    List<Showtime> findShowtimeByMovie(int movieId);
//
//    List<Showtime> findShowtimeByTheater(String theater);

}
