package it.euris.academy.cinema.service;

import java.util.List;
import it.euris.academy.cinema.data.dto.ScreeningDto;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-27
 */

public interface ScreeningService {

  public List<ScreeningDto> getAll();

  public ScreeningDto get(Long id);

  public ScreeningDto add(ScreeningDto screeningDto);

  public ScreeningDto update(ScreeningDto screeningDto);

  public Boolean delete(Long id);
}
