package com.ogrizkov.movieticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Long id;
    private Long userId;
    private Long movieId;
    private Long showtimeId;
    private String seatNumber;
    private int price;
}