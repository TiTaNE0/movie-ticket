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
import com.ogrizkov.movieticket.repository.ShowtimeRepository;
import com.ogrizkov.user.User;
import com.ogrizkov.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;
    private final BookingMapper bookingMapper;
    private final ShowtimeService showtimeService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              MovieRepository movieRepository,
                              ShowtimeRepository showtimeRepository,
                              BookingMapper bookingMapper, ShowtimeService showtimeService) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.showtimeRepository = showtimeRepository;
        this.bookingMapper = bookingMapper;
        this.showtimeService = showtimeService;
    }

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        User user = userRepository.findById(bookingDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Movie movie = movieRepository.findById(bookingDto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        Showtime showtime = showtimeRepository.findById(bookingDto.getShowtimeId())
                .orElseThrow(() -> new ResourceNotFoundException("Showtime not found"));

        if (!isSeatAvailable(showtime, bookingDto.getSeatNumber())) {
            throw new IllegalStateException("Seat is already booked");
        }

        if (showtime.getAvailableSeats() <= 0) {
            throw new IllegalStateException("No more seats available for this showtime");
        }

        Booking booking = bookingMapper.toEntity(bookingDto);
        booking.setUser(user);
        booking.setMovie(movie);
        booking.setShowtime(showtime);

        updateSeatAvailability(showtime, bookingDto.getSeatNumber());

        Booking savedBooking = bookingRepository.save(booking);
        showtimeService.updateSeatAvailability(bookingDto.getShowtimeId(), bookingDto.getSeatNumber(), true);
        return bookingMapper.toDto(savedBooking);
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
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        bookingRepository.delete(booking);
        showtimeService.updateSeatAvailability(booking.getShowtime().getId(), booking.getSeatNumber(), false);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAvailableSeats(Long showtimeId) {
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new ResourceNotFoundException("Showtime not found"));
        List<String> bookedSeats = bookingRepository.findSeatNumbersByShowtimeId(showtimeId);
        return generateAvailableSeats(showtime, bookedSeats);
    }

    private boolean isSeatAvailable(Showtime showtime, String seatNumber) {
        return !bookingRepository.existsByShowtimeAndSeatNumber(showtime, seatNumber);
    }

    private void updateSeatAvailability(Showtime showtime, String seatNumber) {
        showtime.setAvailableSeats(showtime.getAvailableSeats() - 1);
        showtimeRepository.save(showtime);
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