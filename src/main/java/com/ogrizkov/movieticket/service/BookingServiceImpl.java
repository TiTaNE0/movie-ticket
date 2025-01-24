package com.ogrizkov.movieticket.service;

import com.ogrizkov.movieticket.converters.BookingMapper;
import com.ogrizkov.movieticket.dto.BookingDto;
import com.ogrizkov.movieticket.dto.exceptions.ResourceNotFoundException;
import com.ogrizkov.movieticket.model.Booking;
import com.ogrizkov.movieticket.model.Movie;
import com.ogrizkov.movieticket.model.Showtime;
import com.ogrizkov.movieticket.model.Theater;
import com.ogrizkov.movieticket.repository.BookingRepository;
import com.ogrizkov.movieticket.repository.MovieRepository;
import com.ogrizkov.user.User;
import com.ogrizkov.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {


    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final BookingMapper bookingMapper;
    private final ShowtimeService showtimeService;


    @Override
    public BookingDto createBooking(BookingDto bookingDto) {

        Showtime showtime = showtimeService.getShowtimeById(bookingDto.getShowtimeId());
        System.err.println("--------------0---------------------");
        if (!isSeatAvailable(showtime, bookingDto.getSeatNumber())) {
            throw new IllegalArgumentException("Seat is already booked");
        }

        User user = userRepository.findById(bookingDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Movie movie = movieRepository.findById(bookingDto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        if (showtime.getAvailableSeats() <= 0) {
            throw new IllegalArgumentException("No more seats available for this showtime");
        }
        System.err.println("--------------1---------------------");
        Booking booking = bookingMapper.toEntity(bookingDto);
        booking.setUser(user);
        booking.setMovie(movie);
        booking.setShowtime(showtime);
        System.err.println("--------------2---------------------");
        //updateSeatAvailability(showtime, bookingDto.getSeatNumber());
        System.err.println("--------------3---------------------");
        Booking savedBooking = bookingRepository.save(booking);
        System.err.println("--------------4---------------------");
        //showtimeService.updateSeatAvailability(bookingDto.getShowtimeId(), bookingDto.getSeatNumber(), true);
        return null;//bookingMapper.toDto(savedBooking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDto> getBookingsByUser(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDto> getBookingsByShowtime(Long showtimeId) {
        List<Booking> bookings = bookingRepository.findByShowtimeId(showtimeId);
        return bookings.stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        bookingRepository.delete(booking);
        showtimeService.updateSeatAvailability(booking.getShowtime().getId(), booking.getSeatNumber(), false);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAvailableSeats(Long showtimeId) {
        Showtime showtime = showtimeService.getShowtimeById(showtimeId);
        List<String> bookedSeats = bookingRepository.findSeatNumbersByShowtimeId(showtimeId);
        return generateAvailableSeats(showtime, bookedSeats);
    }

    private boolean isSeatAvailable(Showtime showtime, String seatNumber) {
        return !bookingRepository.existsByShowtimeAndSeatNumber(showtime, seatNumber);
    }

    private void updateSeatAvailability(Showtime showtime, String seatNumber) {
        showtime.setAvailableSeats(showtime.getAvailableSeats() - 1);
    }

    private List<String> generateAvailableSeats(Showtime showtime, List<String> bookedSeats) {
        List<String> allSeats = generateAllSeats(showtime.getTheater());
        allSeats.removeAll(bookedSeats);
        return allSeats;
    }

    private List<String> generateAllSeats(Theater theater) {
        // Implementation depends on your seat numbering system
        // This is a placeholder implementation
        List<String> seats = new ArrayList<>();
        for (int i = 1; i <= theater.getCapacity(); i++) {
            seats.add(String.valueOf(i));
        }
        return seats;
    }
}
