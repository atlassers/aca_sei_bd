package it.euris.academy.cinema.service;

import java.util.List;
import it.euris.academy.cinema.data.dto.CinemaDto;
import it.euris.academy.cinema.data.dto.MovieDto;
import it.euris.academy.cinema.data.dto.ScreeningDto;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-24
 */

public interface MovieService {

  public List<MovieDto> getAll();

  public MovieDto get(Long id);

  public MovieDto add(MovieDto movieDto);

  public MovieDto update(MovieDto movieDto);

  public Boolean delete(Long id);
  
  public List<ScreeningDto> getScreenings(Long id);

  public List<CinemaDto> getCinemas(Long id);
}
