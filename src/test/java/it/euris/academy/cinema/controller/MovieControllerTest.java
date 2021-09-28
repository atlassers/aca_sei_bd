package it.euris.academy.cinema.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import it.euris.academy.cinema.data.dto.CinemaDto;
import it.euris.academy.cinema.data.dto.MovieDto;
import it.euris.academy.cinema.data.dto.ScreeningDto;
import it.euris.academy.cinema.data.model.Cinema;
import it.euris.academy.cinema.data.model.Movie;
import it.euris.academy.cinema.data.model.Screening;
import it.euris.academy.cinema.repository.MovieRepository;
import it.euris.academy.cinema.service.MovieService;
import it.euris.academy.cinema.utils.TestSupport;

@SpringBootTest
public class MovieControllerTest {

  @Autowired
  MovieService movieService;

  @MockBean
  MovieRepository movieRepository;

  @Test
  void getAll() {
    List<Movie> mockedMovies = TestSupport.createListOfMovie();
    when(movieRepository.findAll()).thenReturn(mockedMovies);
    
    List<MovieDto> movies = movieService.getAll();
    
    assertEquals(movies.size(), mockedMovies.size());
    for(int i = 0; i < mockedMovies.size(); i++)
      assertEquals(mockedMovies.get(i).toDto(), movies.get(i));
  }

  @Test
  void get() {
    final Long ID = 1L;
    Movie mockedMovie = TestSupport.createMovie(ID);
    when(movieRepository.findById(ID)).thenReturn(Optional.of(mockedMovie));
    
    MovieDto movie = movieService.get(ID);
    
    assertEquals(mockedMovie.toDto(), movie);
  }

  @Test
  void add() {
    Movie movieToSave = TestSupport.createMovie(null);
    Movie movieToReturn = TestSupport.createMovie(10L);
    when(movieRepository.save(any())).thenReturn(movieToReturn);
    
    MovieDto movie = movieService.add(movieToSave.toDto());
    
    assertEquals(movieToReturn.toDto(), movie);
  }

  @Test
  void update() {
    Movie movieToSave = TestSupport.createMovie(3L);
    Movie movieToReturn = TestSupport.createMovie(10L);
    when(movieRepository.save(any())).thenReturn(movieToReturn);
    
    MovieDto movie = movieService.update(movieToSave.toDto());
    
    assertEquals(movieToReturn.toDto(), movie);    
  }

  @Test
  void delete() {
    final Long ID = 1L;
    doNothing().when(movieRepository).deleteById(ID);
    when(movieRepository.findById(ID)).thenReturn(Optional.empty());
    
    boolean deleted = movieService.delete(ID);
    
    assertTrue(deleted);
  }
  
  @Test
  void getScreenings() {
    final Long ID = 1L;
    List<Screening> mockedScreenings = TestSupport.createListOfScreening();
    when(movieRepository.getScreenings(ID)).thenReturn(mockedScreenings);
    
    List<ScreeningDto> screenings = movieService.getScreenings(ID);
    
    assertEquals(mockedScreenings.size(), screenings.size());
    for(int i = 0; i < mockedScreenings.size(); i++)
      assertEquals(mockedScreenings.get(i).toDto(), screenings.get(i));
  }
  
  @Test
  void getCinemas() {
    final Long ID = 1L;
    List<Cinema> mockedCinemas = TestSupport.createListOfCinema();
    when(movieRepository.getCinemas(ID)).thenReturn(mockedCinemas);
    
    List<CinemaDto> cinemas = movieService.getCinemas(ID);
    
    assertEquals(mockedCinemas.size(), cinemas.size());
    for(int i = 0; i < mockedCinemas.size(); i++)
      assertEquals(mockedCinemas.get(i).toDto(), cinemas.get(i));
  }
}
