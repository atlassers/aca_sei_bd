package it.euris.academy.cinema.data.dto;

import it.euris.academy.cinema.data.archetype.Dto;
import it.euris.academy.cinema.data.model.Cinema;
import it.euris.academy.cinema.data.model.Hall;
import it.euris.academy.cinema.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-24
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HallDto implements Dto {

  private String id;
  private String code;
  private String numberOfSeats;
  private String threeDimensional;
  private String cinemaId;

  @Override
  public Hall toModel() {
    return Hall.builder()
        .id(UT.toLong(id))
        .code(code)
        .numberOfSeats(UT.toLong(numberOfSeats))
        .threeDimensional(UT.toBoolean(threeDimensional))
        .cinema(new Cinema(cinemaId))
        .build();
  }
}
