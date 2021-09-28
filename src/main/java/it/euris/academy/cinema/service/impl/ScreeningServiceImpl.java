package it.euris.academy.cinema.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.euris.academy.cinema.data.dto.ScreeningDto;
import it.euris.academy.cinema.data.model.Screening;
import it.euris.academy.cinema.repository.ScreeningRepository;
import it.euris.academy.cinema.service.ScreeningService;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-27
 */

@Service
public class ScreeningServiceImpl implements ScreeningService {

  @Autowired
  private ScreeningRepository screeningRepository;
  
  @Override
  public List<ScreeningDto> getAll() {
    return screeningRepository.findAll().stream().map(Screening::toDto).collect(Collectors.toList());
  }

  @Override
  public ScreeningDto get(Long id) {
    return screeningRepository.findById(id).map(Screening::toDto).orElse(null);
  }

  @Override
  public ScreeningDto add(ScreeningDto screeningDto) {
    return screeningRepository.save(screeningDto.toModel()).toDto();
  }

  @Override
  public ScreeningDto update(ScreeningDto screeningDto) {
    return screeningRepository.save(screeningDto.toModel()).toDto();
  }

  @Override
  public Boolean delete(Long id) {
    screeningRepository.deleteById(id);
    return screeningRepository.findById(id).isEmpty();
  }
}
