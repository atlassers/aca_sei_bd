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
import it.euris.academy.cinema.data.model.Cinema;
import it.euris.academy.cinema.repository.CinemaRepository;
import it.euris.academy.cinema.service.CinemaService;
import it.euris.academy.cinema.utils.TestSupport;

@SpringBootTest
public class CinemaControllerTest {

  @Autowired
  CinemaService cinemaService;

  @MockBean
  CinemaRepository cinemaRepository;

  @Test
  void getAll() {
    List<Cinema> mockedCinemas = TestSupport.createListOfCinema();
    when(cinemaRepository.findAll()).thenReturn(mockedCinemas);
    
    List<CinemaDto> cinemas = cinemaService.getAll();
    
    assertEquals(cinemas.size(), mockedCinemas.size());
    for(int i = 0; i < mockedCinemas.size(); i++)
      assertEquals(mockedCinemas.get(i).toDto(), cinemas.get(i));
  }

  @Test
  void get() {
    final Long ID = 1L;
    Cinema mockedCinema = TestSupport.createCinema(ID);
    when(cinemaRepository.findById(ID)).thenReturn(Optional.of(mockedCinema));
    
    CinemaDto cinema = cinemaService.get(ID);
    
    assertEquals(mockedCinema.toDto(), cinema);
  }

  @Test
  void add() {
    Cinema cinemaToSave = TestSupport.createCinema(null);
    Cinema cinemaToReturn = TestSupport.createCinema(10L);
    when(cinemaRepository.save(any())).thenReturn(cinemaToReturn);
    
    CinemaDto cinema = cinemaService.add(cinemaToSave.toDto());
    
    assertEquals(cinemaToReturn.toDto(), cinema);
  }

  @Test
  void update() {
    Cinema cinemaToSave = TestSupport.createCinema(3L);
    Cinema cinemaToReturn = TestSupport.createCinema(10L);
    when(cinemaRepository.save(any())).thenReturn(cinemaToReturn);
    
    CinemaDto cinema = cinemaService.update(cinemaToSave.toDto());
    
    assertEquals(cinemaToReturn.toDto(), cinema);    
  }

  @Test
  void delete() {
    final Long ID = 1L;
    doNothing().when(cinemaRepository).deleteById(ID);
    when(cinemaRepository.findById(ID)).thenReturn(Optional.empty());
    
    boolean deleted = cinemaService.delete(ID);
    
    assertTrue(deleted);
  }
}
