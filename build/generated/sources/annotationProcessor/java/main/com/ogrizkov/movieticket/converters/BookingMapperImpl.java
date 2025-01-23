package com.ogrizkov.movieticket.converters;

import com.ogrizkov.movieticket.dto.BookingDto;
import com.ogrizkov.movieticket.model.Booking;
import com.ogrizkov.movieticket.model.Movie;
import com.ogrizkov.movieticket.model.Showtime;
import com.ogrizkov.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-23T18:14:42+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 21.0.5 (Ubuntu)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingDto toDto(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingDto bookingDto = new BookingDto();

        bookingDto.setUserId( bookingUserId( booking ) );
        bookingDto.setMovieId( bookingMovieId( booking ) );
        bookingDto.setShowtimeId( bookingShowtimeId( booking ) );
        bookingDto.setId( booking.getId() );
        bookingDto.setSeatNumber( booking.getSeatNumber() );
        bookingDto.setPrice( booking.getPrice() );

        return bookingDto;
    }

    @Override
    public Booking toEntity(BookingDto bookingDto) {
        if ( bookingDto == null ) {
            return null;
        }

        Booking booking = new Booking();

        booking.setId( bookingDto.getId() );
        booking.setSeatNumber( bookingDto.getSeatNumber() );
        booking.setPrice( bookingDto.getPrice() );

        return booking;
    }

    private Long bookingUserId(Booking booking) {
        User user = booking.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private Long bookingMovieId(Booking booking) {
        Movie movie = booking.getMovie();
        if ( movie == null ) {
            return null;
        }
        return movie.getId();
    }

    private Long bookingShowtimeId(Booking booking) {
        Showtime showtime = booking.getShowtime();
        if ( showtime == null ) {
            return null;
        }
        return showtime.getId();
    }
}
