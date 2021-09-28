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
import it.euris.academy.cinema.data.dto.HallDto;
import it.euris.academy.cinema.data.model.Hall;
import it.euris.academy.cinema.repository.HallRepository;
import it.euris.academy.cinema.service.HallService;
import it.euris.academy.cinema.utils.TestSupport;

@SpringBootTest
public class HallControllerTest {

  @Autowired
  HallService hallService;

  @MockBean
  HallRepository hallRepository;

  @Test
  void getAll() {
    List<Hall> mockedHalls = TestSupport.createListOfHall();
    when(hallRepository.findAll()).thenReturn(mockedHalls);
    
    List<HallDto> halls = hallService.getAll();
    
    assertEquals(halls.size(), mockedHalls.size());
    for(int i = 0; i < mockedHalls.size(); i++)
      assertEquals(mockedHalls.get(i).toDto(), halls.get(i));
  }

  @Test
  void get() {
    final Long ID = 1L;
    Hall mockedHall = TestSupport.createHall(ID);
    when(hallRepository.findById(ID)).thenReturn(Optional.of(mockedHall));
    
    HallDto hall = hallService.get(ID);
    
    assertEquals(mockedHall.toDto(), hall);
  }

  @Test
  void add() {
    Hall hallToSave = TestSupport.createHall(null);
    Hall hallToReturn = TestSupport.createHall(10L);
    when(hallRepository.save(any())).thenReturn(hallToReturn);
    
    HallDto hall = hallService.add(hallToSave.toDto());
    
    assertEquals(hallToReturn.toDto(), hall);
  }

  @Test
  void update() {
    Hall hallToSave = TestSupport.createHall(3L);
    Hall hallToReturn = TestSupport.createHall(10L);
    when(hallRepository.save(any())).thenReturn(hallToReturn);
    
    HallDto hall = hallService.update(hallToSave.toDto());
    
    assertEquals(hallToReturn.toDto(), hall);    
  }

  @Test
  void delete() {
    final Long ID = 1L;
    doNothing().when(hallRepository).deleteById(ID);
    when(hallRepository.findById(ID)).thenReturn(Optional.empty());
    
    boolean deleted = hallService.delete(ID);
    
    assertTrue(deleted);
  }
}
