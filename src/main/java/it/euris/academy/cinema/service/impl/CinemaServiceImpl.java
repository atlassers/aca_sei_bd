package it.euris.academy.cinema.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.euris.academy.cinema.data.dto.CinemaDto;
import it.euris.academy.cinema.data.model.Cinema;
import it.euris.academy.cinema.exception.IdMustBeNullException;
import it.euris.academy.cinema.exception.IdMustNotBeNullException;
import it.euris.academy.cinema.repository.CinemaRepository;
import it.euris.academy.cinema.service.CinemaService;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-24
 */

@Service
public class CinemaServiceImpl implements CinemaService {

  @Autowired
  private CinemaRepository cinemaRepository;

  @Override
  public List<CinemaDto> getAll() {
    return cinemaRepository.findAll().stream().map(Cinema::toDto).collect(Collectors.toList());
  }

  @Override
  public CinemaDto get(Long id) {
    return cinemaRepository.findById(id).map(Cinema::toDto).orElse(null);
  }

  @Override
  public CinemaDto add(CinemaDto cinemaDto) {
    if (cinemaDto.getId() != null)
      throw new IdMustBeNullException();

    return cinemaRepository.save(cinemaDto.toModel()).toDto();
  }

  @Override
  public CinemaDto update(CinemaDto cinemaDto) {
    if (cinemaDto.getId() == null)
      throw new IdMustNotBeNullException();

    return cinemaRepository.save(cinemaDto.toModel()).toDto();
  }

  @Override
  public Boolean delete(Long id) {
    cinemaRepository.deleteById(id);
    return cinemaRepository.findById(id).isEmpty();
  }
}
