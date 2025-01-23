package com.ogrizkov.movieticket.converters;

import com.ogrizkov.movieticket.dto.MovieDto;
import com.ogrizkov.movieticket.model.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-23T15:05:46+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 21.0.5 (Ubuntu)"
)
@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public MovieDto toDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDto movieDto = new MovieDto();

        movieDto.setId( movie.getId() );
        movieDto.setTitle( movie.getTitle() );
        movieDto.setGenre( movie.getGenre() );
        movieDto.setDuration( movie.getDuration() );
        movieDto.setRating( movie.getRating() );
        movieDto.setReleaseYear( movie.getReleaseYear() );

        return movieDto;
    }

    @Override
    public Movie toEntity(MovieDto movieDto) {
        if ( movieDto == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setTitle( movieDto.getTitle() );
        movie.setGenre( movieDto.getGenre() );
        movie.setDuration( movieDto.getDuration() );
        movie.setRating( movieDto.getRating() );
        movie.setReleaseYear( movieDto.getReleaseYear() );

        return movie;
    }

    @Override
    public void updateMovieFromDto(MovieDto movieDto, Movie movie) {
        if ( movieDto == null ) {
            return;
        }

        movie.setTitle( movieDto.getTitle() );
        movie.setGenre( movieDto.getGenre() );
        movie.setDuration( movieDto.getDuration() );
        movie.setRating( movieDto.getRating() );
        movie.setReleaseYear( movieDto.getReleaseYear() );
    }

    @Override
    public List<MovieDto> toDtoList(List<Movie> movies) {
        if ( movies == null ) {
            return null;
        }

        List<MovieDto> list = new ArrayList<MovieDto>( movies.size() );
        for ( Movie movie : movies ) {
            list.add( toDto( movie ) );
        }

        return list;
    }
}
