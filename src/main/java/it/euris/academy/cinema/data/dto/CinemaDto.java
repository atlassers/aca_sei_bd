package it.euris.academy.cinema.data.dto;

import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.cinema.data.archetype.Dto;
import it.euris.academy.cinema.data.model.Cinema;
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
public class CinemaDto implements Dto {

  private String id;
  private String name;
  private String address;

  @JsonIgnore
  private List<HallDto> halls;

  @Override
  public Cinema toModel() {
    return Cinema.builder()
        .id(UT.toLong(id))
        .name(name)
        .address(address)
        .halls(halls == null ? null : halls.stream().map(HallDto::toModel).collect(Collectors.toList()))
        .build();
  }
}
