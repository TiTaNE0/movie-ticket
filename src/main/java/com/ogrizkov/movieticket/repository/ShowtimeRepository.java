package com.ogrizkov.movieticket.repository;

import com.ogrizkov.movieticket.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    List<Showtime> findByMovieId(Long movieId);

    List<Showtime> findByTheaterId(Long theaterId);

    List<Showtime> findByTheaterIdAndStartTimeBetween(Long theaterId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT s FROM Showtime s WHERE s.theater.id = :theaterId AND " +
            "(:startTime < s.endTime AND :endTime > s.startTime)")
    List<Showtime> findOverlappingShowtimes(@Param("theaterId") Long theaterId,
                                            @Param("startTime") LocalDateTime startTime,
                                            @Param("endTime") LocalDateTime endTime);

}
