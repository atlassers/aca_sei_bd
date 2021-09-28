package it.euris.academy.cinema.data.dto;

import it.euris.academy.cinema.data.archetype.Dto;
import it.euris.academy.cinema.data.model.Movie;
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
public class MovieDto implements Dto {

  private String id;
  private String name;
  private String length;
  private String releaseDate;
  
  @Override
  public Movie toModel() {
    return Movie.builder()
        .id(UT.toLong(id))
        .name(name)
        .length(UT.toLong(length))
        .releaseDate(UT.toInstant(releaseDate))
        .build();
  }
}
