package com.ogrizkov.movieticket.service;

import com.ogrizkov.movieticket.dto.MovieDto;
import com.ogrizkov.movieticket.dto.NewMovieDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {

    MovieDto getMovie(Long id);

    MovieDto addMovie(NewMovieDto movie);

    void updateMovie(int id);

    void deleteMovie(int id);

    List<MovieDto> getAllMovies();

    List<MovieDto> getMoviesByGenre(String genre);

    List<MovieDto> getMoviesByRatingFromTo(int from, int to);

    List<MovieDto> getMoviesByYear(int year);

}
