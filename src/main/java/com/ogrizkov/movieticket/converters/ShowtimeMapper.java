package com.ogrizkov.movieticket.converters;

import com.ogrizkov.movieticket.dto.ShowtimeDto;
import com.ogrizkov.movieticket.model.Showtime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {

    // Convert Showtime entity to ShowtimeDto
    @Mapping(source = "movie.id", target = "movieId")
    @Mapping(source = "theater.id", target = "theaterId")
    ShowtimeDto toDto(Showtime showtime);

    // Convert ShowtimeDto to Showtime entity
    @Mapping(target = "id", ignore = true) // Ignore ID when creating a new entity
    @Mapping(target = "movie", ignore = true) // Movie will be set manually in the service
    @Mapping(target = "theater", ignore = true) // Theater will be set manually in the service
    Showtime toEntity(ShowtimeDto showtimeDto);

    // Update an existing Showtime entity from a ShowtimeDto
    @Mapping(target = "id", ignore = true) // Prevent overwriting the ID
    @Mapping(target = "movie", ignore = true) // Movie will be set manually in the service
    @Mapping(target = "theater", ignore = true) // Theater will be set manually in the service
    void updateShowtimeFromDto(ShowtimeDto showtimeDto, @MappingTarget Showtime showtime);
}
