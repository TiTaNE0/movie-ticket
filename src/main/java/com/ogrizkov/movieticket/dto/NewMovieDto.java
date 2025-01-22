package com.ogrizkov.movieticket.dto;

import lombok.Getter;

public record NewMovieDto(
        String title,
        String genre,
        int duration,
        int rating,
        int releaseYear
)   {}