package com.ogrizkov.movieticket.converters;

import com.ogrizkov.movieticket.dto.ShowtimeDto;
import com.ogrizkov.movieticket.model.Movie;
import com.ogrizkov.movieticket.model.Showtime;
import com.ogrizkov.movieticket.model.Theater;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-23T18:10:31+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 21.0.5 (Ubuntu)"
)
@Component
public class ShowtimeMapperImpl implements ShowtimeMapper {

    @Override
    public ShowtimeDto toDto(Showtime showtime) {
        if ( showtime == null ) {
            return null;
        }

        ShowtimeDto showtimeDto = new ShowtimeDto();

        showtimeDto.setMovieId( showtimeMovieId( showtime ) );
        showtimeDto.setTheaterId( showtimeTheaterId( showtime ) );
        showtimeDto.setId( showtime.getId() );
        showtimeDto.setStartTime( showtime.getStartTime() );
        showtimeDto.setEndTime( showtime.getEndTime() );

        return showtimeDto;
    }

    @Override
    public Showtime toEntity(ShowtimeDto showtimeDto) {
        if ( showtimeDto == null ) {
            return null;
        }

        Showtime showtime = new Showtime();

        showtime.setStartTime( showtimeDto.getStartTime() );
        showtime.setEndTime( showtimeDto.getEndTime() );

        return showtime;
    }

    @Override
    public void updateShowtimeFromDto(ShowtimeDto showtimeDto, Showtime showtime) {
        if ( showtimeDto == null ) {
            return;
        }

        showtime.setStartTime( showtimeDto.getStartTime() );
        showtime.setEndTime( showtimeDto.getEndTime() );
    }

    private Long showtimeMovieId(Showtime showtime) {
        Movie movie = showtime.getMovie();
        if ( movie == null ) {
            return null;
        }
        return movie.getId();
    }

    private Long showtimeTheaterId(Showtime showtime) {
        Theater theater = showtime.getTheater();
        if ( theater == null ) {
            return null;
        }
        return theater.getId();
    }
}
