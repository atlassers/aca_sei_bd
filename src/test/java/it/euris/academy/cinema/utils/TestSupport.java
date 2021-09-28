package it.euris.academy.cinema.utils;

import java.time.Instant;
import java.util.List;
import it.euris.academy.cinema.data.model.Cinema;
import it.euris.academy.cinema.data.model.Hall;
import it.euris.academy.cinema.data.model.Movie;
import it.euris.academy.cinema.data.model.Screening;

public class TestSupport {

  public static Cinema createCinema(Long id) {
    return Cinema.builder()
        .id(id)
        .name("CineStar")
        .address("via Garibaldi 10, Milano")
        .build();
  }

  public static List<Cinema> createListOfCinema() {
    return List.of(createCinema(1L), createCinema(2L));
  }

  public static Movie createMovie(Long id) {
    return Movie.builder()
        .id(id)
        .name("Il Signore Degli Anelli: La Compagnia Dell'Anello")
        .length(300L)
        .releaseDate(Instant.parse("2001-01-09T00:00:00Z"))
        .build();
  }

  public static List<Movie> createListOfMovie() {
    return List.of(createMovie(1L), createMovie(2L));
  }
  
  public static Hall createHall(Long id) {
    return Hall.builder()
        .id(id)
        .code("1")
        .numberOfSeats(100L)
        .threeDimensional(false)
        .cinema(createCinema(1L))
        .build();
  }
  
  public static List<Hall> createListOfHall() {
    return List.of(createHall(1L), createHall(2L));
  }
  
  public static Screening createScreening(Long id) {
    return Screening.builder()
        .id(id)
        .date(Instant.parse("2021-01-09T00:00:00Z"))
        .hall(createHall(1L))
        .movie(createMovie(1L))
        .build();
  }
  
  public static List<Screening> createListOfScreening() {
    return List.of(createScreening(1L), createScreening(2L));
  }
}
