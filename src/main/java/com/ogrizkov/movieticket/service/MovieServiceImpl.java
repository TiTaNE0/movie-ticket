package com.ogrizkov.movieticket.service;

import com.ogrizkov.movieticket.dto.MovieDto;
import com.ogrizkov.movieticket.dto.NewMovieDto;
import com.ogrizkov.movieticket.dto.exceptions.NoSuchMovieException;
import com.ogrizkov.movieticket.model.Movie;
import com.ogrizkov.movieticket.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public MovieDto getMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new NoSuchMovieException(id));
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public MovieDto addMovie(NewMovieDto newMovie) {
        Movie movie = new Movie(newMovie.title(), newMovie.genre(), newMovie.duration(), newMovie.rating(), newMovie.releaseYear());
        movieRepository.save(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public void updateMovie(int id) {

    }

    @Override
    public void deleteMovie(int id) {

    }

    @Override
    public List<MovieDto> getAllMovies() {
        return List.of();
    }

    @Override
    public List<MovieDto> getMoviesByGenre(String genre) {
        return List.of();
    }

    @Override
    public List<MovieDto> getMoviesByRatingFromTo(int from, int to) {
        return List.of();
    }

    @Override
    public List<MovieDto> getMoviesByYear(int year) {
        return List.of();
    }
}
