package com.ogrizkov.movieticket.service;

import com.ogrizkov.movieticket.dto.ShowtimeDto;

import java.util.List;

public interface ShowtimeService {

    ShowtimeDto addShowtime(ShowtimeDto showtimeDto);

    ShowtimeDto updateShowtime(Long id, ShowtimeDto showtimeDto);

    void deleteShowtime(Long id);

    List<ShowtimeDto> getShowtimesByMovie(Long movieId);

    List<ShowtimeDto> getShowtimesByTheater(Long theaterId);

    void updateSeatAvailability(Long showtimeId, String seatNumber, boolean isBooked);

    boolean isOverlapping(ShowtimeDto showtimeDto);
}
