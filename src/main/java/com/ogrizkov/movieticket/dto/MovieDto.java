package com.ogrizkov.movieticket.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    Long id;
    String title;
    String genre;
    int duration;
    int rating;
    int releaseYear;
}