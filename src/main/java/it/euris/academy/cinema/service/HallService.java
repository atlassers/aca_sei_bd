package it.euris.academy.cinema.service;

import java.util.List;
import it.euris.academy.cinema.data.dto.HallDto;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-24
 */

public interface HallService {

  public List<HallDto> getAll();

  public HallDto get(Long id);

  public HallDto add(HallDto hallDto);

  public HallDto update(HallDto hallDto);

  public Boolean delete(Long id);
}
