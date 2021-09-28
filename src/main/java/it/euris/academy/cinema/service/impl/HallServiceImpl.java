package it.euris.academy.cinema.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.euris.academy.cinema.data.dto.HallDto;
import it.euris.academy.cinema.data.model.Hall;
import it.euris.academy.cinema.exception.IdMustBeNullException;
import it.euris.academy.cinema.exception.IdMustNotBeNullException;
import it.euris.academy.cinema.repository.HallRepository;
import it.euris.academy.cinema.service.HallService;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-24
 */

@Service
public class HallServiceImpl implements HallService {

  @Autowired
  HallRepository hallRepository;

  @Override
  public List<HallDto> getAll() {
    return hallRepository.findAll().stream().map(Hall::toDto).collect(Collectors.toList());
  }

  @Override
  public HallDto get(Long id) {
    return hallRepository.findById(id).map(Hall::toDto).orElse(null);
  }

  @Override
  public HallDto add(HallDto hallDto) {
    if (hallDto.getId() != null)
      throw new IdMustBeNullException();

    return hallRepository.save(hallDto.toModel()).toDto();
  }

  @Override
  public HallDto update(HallDto hallDto) {
    if (hallDto.getId() == null)
      throw new IdMustNotBeNullException();

    return hallRepository.save(hallDto.toModel()).toDto();
  }

  @Override
  public Boolean delete(Long id) {
    hallRepository.deleteById(id);
    return hallRepository.findById(id).isEmpty();
  }
}
