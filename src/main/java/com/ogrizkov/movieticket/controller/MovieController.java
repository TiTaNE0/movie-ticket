package com.ogrizkov.movieticket.controller;

import com.ogrizkov.movieticket.dto.MovieDto;
import com.ogrizkov.movieticket.dto.NewMovieDto;
import com.ogrizkov.movieticket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public MovieDto addMovie(@RequestBody NewMovieDto newMovie) {
        return movieService.addMovie(newMovie);
    }

}
