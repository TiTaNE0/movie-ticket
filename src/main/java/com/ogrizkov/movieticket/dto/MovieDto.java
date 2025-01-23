package com.ogrizkov.movieticket.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    Long id;
    String title;
    String genre;

    @Min(0)
    int duration;

    @Min(0)
    @Max(100)
    int rating;
    int releaseYear;
}
