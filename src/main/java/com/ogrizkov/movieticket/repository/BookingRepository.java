package com.ogrizkov.movieticket.repository;

import com.ogrizkov.movieticket.model.Booking;
import com.ogrizkov.movieticket.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByShowtimeAndSeatNumber(Showtime showtime, String seatNumber);

    long countByShowtime(Showtime showtime);

    List<Booking> findByUserId(Long userId);

    List<Booking> findByShowtimeId(Long showtimeId);

    List<String> findSeatNumbersByShowtimeId(Long showtimeId);

}
