package com.ogrizkov.movieticket.service;

import com.ogrizkov.movieticket.model.Movie;
import com.ogrizkov.movieticket.model.Showtime;

import java.util.List;

public interface IShowtime {

    void addShowtime(Movie movie, String theater, int startTime, int endTime);

    void updateShowtime(Movie movie, String theater, int startTime, int endTime);

    void deleteShowtime(int id);

    List<Showtime> getAllShowTimes();

    List<Showtime> getShowTimesByMovie(Movie movie);

    List<Showtime> getShowTimesByTheater(String theater);
}
