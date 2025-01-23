package com.ogrizkov.movieticket.service;

import com.ogrizkov.movieticket.dto.BookingDto;

import java.util.List;

public interface BookingService {
    BookingDto createBooking(BookingDto bookingDto);

    List<BookingDto> getBookingsByUser(Long userId);

    List<BookingDto> getBookingsByShowtime(Long showtimeId);

    void cancelBooking(Long bookingId);

    List<String> getAvailableSeats(Long showtimeId);
}
