package com.ogrizkov.movieticket.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"movie", "theater"})
@ToString(exclude = {"movie", "theater"})
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Theater theater;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ElementCollection
    @CollectionTable(name = "showtime_seats")
    private List<List<Boolean>> seats;

    private Integer availableSeats;
}