package com.ogrizkov.movieticket.controller;

import com.ogrizkov.movieticket.dto.ShowtimeDto;
import com.ogrizkov.movieticket.dto.exceptions.ResourceNotFoundException;
import com.ogrizkov.movieticket.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @Autowired
    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @PostMapping
    public ResponseEntity<ShowtimeDto> addShowtime(@RequestBody ShowtimeDto showtimeDto) {
        try {
            ShowtimeDto createdShowtime = showtimeService.addShowtime(showtimeDto);
            return new ResponseEntity<>(createdShowtime, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowtimeDto> updateShowtime(@PathVariable Long id, @RequestBody ShowtimeDto showtimeDto) {
        try {
            ShowtimeDto updatedShowtime = showtimeService.updateShowtime(id, showtimeDto);
            return ResponseEntity.ok(updatedShowtime);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Long id) {
        try {
            showtimeService.deleteShowtime(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowtimeDto>> getShowtimesByMovie(@PathVariable Long movieId) {
        List<ShowtimeDto> showtimes = showtimeService.getShowtimesByMovie(movieId);
        return ResponseEntity.ok(showtimes);
    }

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<ShowtimeDto>> getShowtimesByTheater(@PathVariable Long theaterId) {
        List<ShowtimeDto> showtimes = showtimeService.getShowtimesByTheater(theaterId);
        return ResponseEntity.ok(showtimes);
    }
}
