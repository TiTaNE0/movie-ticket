package com.ogrizkov.movieticket.controller;

import com.ogrizkov.movieticket.dto.BookingDto;
import com.ogrizkov.movieticket.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
        BookingDto createdBooking = bookingService.createBooking(bookingDto);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDto>> getBookingsByUser(@PathVariable Long userId) {
        List<BookingDto> bookings = bookingService.getBookingsByUser(userId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/showtime/{showtimeId}")
    public ResponseEntity<List<BookingDto>> getBookingsByShowtime(@PathVariable Long showtimeId) {
        List<BookingDto> bookings = bookingService.getBookingsByShowtime(showtimeId);
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available-seats/{showtimeId}")
    public ResponseEntity<List<String>> getAvailableSeats(@PathVariable Long showtimeId) {
        List<String> availableSeats = bookingService.getAvailableSeats(showtimeId);
        return ResponseEntity.ok(availableSeats);
    }
}
