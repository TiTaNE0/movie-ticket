//package com.ogrizkov.movieticket;
//
//import com.ogrizkov.movieticket.converters.BookingMapper;
//import com.ogrizkov.movieticket.dto.BookingDto;
//import com.ogrizkov.movieticket.model.Booking;
//import com.ogrizkov.movieticket.model.Movie;
//import com.ogrizkov.movieticket.model.Showtime;
//import com.ogrizkov.movieticket.model.Theater;
//import com.ogrizkov.movieticket.repository.BookingRepository;
//import com.ogrizkov.movieticket.repository.MovieRepository;
//import com.ogrizkov.movieticket.repository.ShowtimeRepository;
//import com.ogrizkov.movieticket.service.BookingServiceImpl;
//import com.ogrizkov.user.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class BookingServiceImplTest {
//
//    @Mock
//    private BookingRepository bookingRepository;
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private MovieRepository movieRepository;
//    @Mock
//    private ShowtimeRepository showtimeRepository;
//    @Mock
//    private BookingMapper bookingMapper;
//
//    @InjectMocks
//    private BookingServiceImpl bookingService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void createBooking_Success() {
//        // Arrange
//        BookingDto bookingDto = new BookingDto(null, 1L, 1L, 1L, "A1", 1000);
//        User user = new User(1L, "John Doe", "john@example.com");
//        Movie movie = new Movie(1L, "Test Movie", 120);
//        Showtime showtime = new Showtime(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now().plusHours(2), null, 50);
//        Booking booking = new Booking(1L, user, movie, showtime, "A1", 1000);
//
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
//        when(showtimeRepository.findById(1L)).thenReturn(Optional.of(showtime));
//        when(bookingRepository.existsByShowtimeAndSeatNumber(showtime, "A1")).thenReturn(false);
//        when(bookingMapper.toEntity(bookingDto)).thenReturn(booking);
//        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
//        when(bookingMapper.toDto(booking)).thenReturn(bookingDto);
//
//        // Act
//        BookingDto result = bookingService.createBooking(bookingDto);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(bookingDto, result);
//        verify(showtimeRepository).save(showtime);
//    }
//
//    @Test
//    void createBooking_SeatAlreadyBooked() {
//        // Arrange
//        BookingDto bookingDto = new BookingDto(null, 1L, 1L, 1L, "A1", 1000);
//        User user = new User(1L, "John Doe", "john@example.com");
//        Movie movie = new Movie(1L, "Test Movie", 120);
//        Showtime showtime = new Showtime(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now().plusHours(2), null, 50);
//
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
//        when(showtimeRepository.findById(1L)).thenReturn(Optional.of(showtime));
//        when(bookingRepository.existsByShowtimeAndSeatNumber(showtime, "A1")).thenReturn(true);
//
//        // Act & Assert
//        assertThrows(IllegalStateException.class, () -> bookingService.createBooking(bookingDto));
//    }
//
//    @Test
//    void getBookingsByUser_Success() {
//        // Arrange
//        Long userId = 1L;
//        List<Booking> bookings = Arrays.asList(
//                new Booking(1L, new User(), new Movie(), new Showtime(), "A1", 1000),
//                new Booking(2L, new User(), new Movie(), new Showtime(), "B2", 1000)
//        );
//        List<BookingDto> bookingDtos = Arrays.asList(
//                new BookingDto(1L, userId, 1L, 1L, "A1", 1000),
//                new BookingDto(2L, userId, 1L, 1L, "B2", 1000)
//        );
//
//        when(bookingRepository.findByUserId(userId)).thenReturn(bookings);
//        when(bookingMapper.toDto(any(Booking.class))).thenReturn(bookingDtos.get(0), bookingDtos.get(1));
//
//        // Act
//        List<BookingDto> result = bookingService.getBookingsByUser(userId);
//
//        // Assert
//        assertEquals(2, result.size());
//        assertEquals(bookingDtos, result);
//    }
//
//    @Test
//    void cancelBooking_Success() {
//        // Arrange
//        Long bookingId = 1L;
//        Booking booking = new Booking(1L, new User(), new Movie(), new Showtime(), "A1", 1000);
//        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
//
//        // Act
//        bookingService.cancelBooking(bookingId);
//
//        // Assert
//        verify(bookingRepository).delete(booking);
//        verify(showtimeRepository).save(booking.getShowtime());
//    }
//
//    @Test
//    void getAvailableSeats_Success() {
//        // Arrange
//        Long showtimeId = 1L;
//        Showtime showtime = new Showtime(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now().plusHours(2), null, 50);
//        Theater theater = new Theater(1L, "Theater 1", 50, 5, 10);
//        List<String> bookedSeats = Arrays.asList("A1", "B2");
//
//        when(showtimeRepository.findById(showtimeId)).thenReturn(Optional.of(showtime));
//        when(bookingRepository.findSeatNumbersByShowtimeId(showtimeId)).thenReturn(bookedSeats);
//
//        // Act
//        List<String> result = bookingService.getAvailableSeats(showtimeId);
//
//        // Assert
//        assertEquals(48, result.size());
//        assertFalse(result.contains("A1"));
//        assertFalse(result.contains("B2"));
//    }
//}
