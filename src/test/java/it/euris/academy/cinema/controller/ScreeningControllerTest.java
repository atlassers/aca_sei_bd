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
import it.euris.academy.cinema.data.dto.ScreeningDto;
import it.euris.academy.cinema.data.model.Screening;
import it.euris.academy.cinema.repository.ScreeningRepository;
import it.euris.academy.cinema.service.ScreeningService;
import it.euris.academy.cinema.utils.TestSupport;

@SpringBootTest
public class ScreeningControllerTest {

  @Autowired
  ScreeningService screeningService;

  @MockBean
  ScreeningRepository screeningRepository;

  @Test
  void getAll() {
    List<Screening> mockedScreenings = TestSupport.createListOfScreening();
    when(screeningRepository.findAll()).thenReturn(mockedScreenings);
    
    List<ScreeningDto> screenings = screeningService.getAll();
    
    assertEquals(screenings.size(), mockedScreenings.size());
    for(int i = 0; i < mockedScreenings.size(); i++)
      assertEquals(mockedScreenings.get(i).toDto(), screenings.get(i));
  }

  @Test
  void get() {
    final Long ID = 1L;
    Screening mockedScreening = TestSupport.createScreening(ID);
    when(screeningRepository.findById(ID)).thenReturn(Optional.of(mockedScreening));
    
    ScreeningDto screening = screeningService.get(ID);
    
    assertEquals(mockedScreening.toDto(), screening);
  }

  @Test
  void add() {
    Screening screeningToSave = TestSupport.createScreening(null);
    Screening screeningToReturn = TestSupport.createScreening(10L);
    when(screeningRepository.save(any())).thenReturn(screeningToReturn);
    
    ScreeningDto screening = screeningService.add(screeningToSave.toDto());
    
    assertEquals(screeningToReturn.toDto(), screening);
  }

  @Test
  void update() {
    Screening screeningToSave = TestSupport.createScreening(3L);
    Screening screeningToReturn = TestSupport.createScreening(10L);
    when(screeningRepository.save(any())).thenReturn(screeningToReturn);
    
    ScreeningDto screening = screeningService.update(screeningToSave.toDto());
    
    assertEquals(screeningToReturn.toDto(), screening);    
  }

  @Test
  void delete() {
    final Long ID = 1L;
    doNothing().when(screeningRepository).deleteById(ID);
    when(screeningRepository.findById(ID)).thenReturn(Optional.empty());
    
    boolean deleted = screeningService.delete(ID);
    
    assertTrue(deleted);
  }
}
