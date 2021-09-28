package it.euris.academy.cinema.data.dto;

import it.euris.academy.cinema.data.archetype.Dto;
import it.euris.academy.cinema.data.model.Hall;
import it.euris.academy.cinema.data.model.Movie;
import it.euris.academy.cinema.data.model.Screening;
import it.euris.academy.cinema.utils.UT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-27
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreeningDto implements Dto {

  private String hallId;
  private String movieId;
  private String date;

  @Override
  public Screening toModel() {
    return Screening.builder()
        .hall(Hall.builder().id(UT.toLong(hallId)).build())
        .movie(Movie.builder().id(UT.toLong(movieId)).build())
        .date(UT.toInstant(date))
        .build();
  }
}
