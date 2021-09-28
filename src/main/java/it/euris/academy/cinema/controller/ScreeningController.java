package it.euris.academy.cinema.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.euris.academy.cinema.data.dto.ScreeningDto;
import it.euris.academy.cinema.service.ScreeningService;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-27
 */

@RestController
@RequestMapping("/screenings")
public class ScreeningController {

  @Autowired
  private ScreeningService screeningService;
  
  @GetMapping("/v1")
  public List<ScreeningDto> getAll(){
    return screeningService.getAll();
  }

  @GetMapping("/v1/{id}")
  public ScreeningDto get(@PathVariable("id") Long id) {
    return screeningService.get(id);
  }

  @PostMapping("/v1")
  public ScreeningDto add(@RequestBody ScreeningDto screeningDto) {
    return screeningService.add(screeningDto);
  }

  @PutMapping("/v1")
  public ScreeningDto update(@RequestBody ScreeningDto screeningDto) {
    return screeningService.update(screeningDto);
  }

  @DeleteMapping("/v1/{id}")
  public Boolean delete(@PathVariable("id") Long id) {
    return screeningService.delete(id);
  }
}
