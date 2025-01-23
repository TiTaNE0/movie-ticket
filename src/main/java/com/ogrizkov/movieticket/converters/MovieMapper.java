package com.ogrizkov.movieticket.converters;

import com.ogrizkov.movieticket.dto.MovieDto;
import com.ogrizkov.movieticket.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDto toDto(Movie movie);

    @Mapping(target = "id", ignore = true)
    Movie toEntity(MovieDto movieDto);

    @Mapping(target = "id", ignore = true)
    void updateMovieFromDto(MovieDto movieDto, @MappingTarget Movie movie);

    List<MovieDto> toDtoList(List<Movie> movies);
}
