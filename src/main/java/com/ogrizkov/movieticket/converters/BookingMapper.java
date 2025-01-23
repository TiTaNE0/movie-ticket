package com.ogrizkov.movieticket.converters;

import com.ogrizkov.movieticket.dto.BookingDto;
import com.ogrizkov.movieticket.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "movie.id", target = "movieId")
    @Mapping(source = "showtime.id", target = "showtimeId")
    BookingDto toDto(Booking booking);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "showtime", ignore = true)
    Booking toEntity(BookingDto bookingDto);
}

