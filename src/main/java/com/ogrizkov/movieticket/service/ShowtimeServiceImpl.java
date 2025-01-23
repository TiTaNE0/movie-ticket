package com.ogrizkov.movieticket.service;

import com.ogrizkov.movieticket.converters.ShowtimeMapper;
import com.ogrizkov.movieticket.dto.ShowtimeDto;
import com.ogrizkov.movieticket.dto.exceptions.ResourceNotFoundException;
import com.ogrizkov.movieticket.model.Movie;
import com.ogrizkov.movieticket.model.Showtime;
import com.ogrizkov.movieticket.model.Theater;
import com.ogrizkov.movieticket.repository.MovieRepository;
import com.ogrizkov.movieticket.repository.ShowtimeRepository;
import com.ogrizkov.movieticket.repository.TheaterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShowtimeServiceImpl implements ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ShowtimeMapper showtimeMapper;

    public ShowtimeServiceImpl(ShowtimeRepository showtimeRepository,
                               MovieRepository movieRepository,
                               TheaterRepository theaterRepository,
                               ShowtimeMapper showtimeMapper) {
        this.showtimeRepository = showtimeRepository;
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
        this.showtimeMapper = showtimeMapper;
    }

    @Override
    public ShowtimeDto addShowtime(ShowtimeDto showtimeDto) {
        if (isOverlapping(showtimeDto)) {
            throw new IllegalArgumentException("Showtime overlaps with an existing showtime in the same theater.");
        }
        Showtime showtime = showtimeMapper.toEntity(showtimeDto);
        Movie movie = movieRepository.findById(showtimeDto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        Theater theater = theaterRepository.findById(showtimeDto.getTheaterId())
                .orElseThrow(() -> new ResourceNotFoundException("Theater not found"));
        showtime.setMovie(movie);
        showtime.setTheater(theater);

        // Initialize seats and availableSeats
        initializeSeats(showtime, theater);

        Showtime savedShowtime = showtimeRepository.save(showtime);
        return showtimeMapper.toDto(savedShowtime);
    }

    private void initializeSeats(Showtime showtime, Theater theater) {
        int rows = theater.getRows();
        int seatsPerRow = theater.getSeatsPerRow();
        List<List<Boolean>> seats = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            List<Boolean> row = new ArrayList<>(Collections.nCopies(seatsPerRow, false));
            seats.add(row);
        }
        showtime.setSeats(seats);
        showtime.setAvailableSeats(rows * seatsPerRow);
    }

    @Override
    public ShowtimeDto updateShowtime(Long id, ShowtimeDto showtimeDto) {
        Showtime existingShowtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Showtime not found"));
        if (isOverlapping(showtimeDto) && !existingShowtime.getId().equals(showtimeDto.getId())) {
            throw new IllegalArgumentException("Updated showtime overlaps with an existing showtime in the same theater.");
        }
        showtimeMapper.updateShowtimeFromDto(showtimeDto, existingShowtime);
        existingShowtime.setMovie(movieRepository.findById(showtimeDto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found")));
        existingShowtime.setTheater(theaterRepository.findById(showtimeDto.getTheaterId())
                .orElseThrow(() -> new ResourceNotFoundException("Theater not found")));
        Showtime updatedShowtime = showtimeRepository.save(existingShowtime);
        return showtimeMapper.toDto(updatedShowtime);
    }

    @Override
    public void deleteShowtime(Long id) {
        if (!showtimeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Showtime not found");
        }
        showtimeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShowtimeDto> getShowtimesByMovie(Long movieId) {
        List<Showtime> showtimes = showtimeRepository.findByMovieId(movieId);
        return showtimes.stream()
                .map(showtimeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShowtimeDto> getShowtimesByTheater(Long theaterId) {
        List<Showtime> showtimes = showtimeRepository.findByTheaterId(theaterId);
        return showtimes.stream()
                .map(showtimeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isOverlapping(ShowtimeDto showtimeDto) {
        List<Showtime> existingShowtimes = showtimeRepository.findOverlappingShowtimes(
                showtimeDto.getTheaterId(),
                showtimeDto.getStartTime(),
                showtimeDto.getEndTime());
        return !existingShowtimes.isEmpty();
    }

    @Override
    public void updateSeatAvailability(Long showtimeId, String seatNumber, boolean isBooked) {
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new ResourceNotFoundException("Showtime not found"));

        int row = seatNumber.charAt(0) - 'A';
        int seatInRow = Integer.parseInt(seatNumber.substring(1)) - 1;

        List<List<Boolean>> seats = showtime.getSeats();
        if (seats.get(row).get(seatInRow) != isBooked) {
            seats.get(row).set(seatInRow, isBooked);
            showtime.setSeats(seats);
            showtime.setAvailableSeats(showtime.getAvailableSeats() + (isBooked ? -1 : 1));
            showtimeRepository.save(showtime);
        }
    }
}

