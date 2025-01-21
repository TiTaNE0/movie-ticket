package com.ogrizkov.movieticket.model;

public record Movie(
        int id,
        String title,
        String Genre,
        int duration,
        int rating,
        int releaseYear
) {

}
