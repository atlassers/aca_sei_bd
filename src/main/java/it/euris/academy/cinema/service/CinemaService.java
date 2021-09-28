package it.euris.academy.cinema.service;

import java.util.List;
import it.euris.academy.cinema.data.dto.CinemaDto;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-24
 */

public interface CinemaService {

  public List<CinemaDto> getAll();

  public CinemaDto get(Long id);

  public CinemaDto add(CinemaDto cinemaDto);

  public CinemaDto update(CinemaDto cinemaDto);

  public Boolean delete(Long id);
}
