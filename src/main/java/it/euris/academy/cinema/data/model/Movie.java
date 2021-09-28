package it.euris.academy.cinema.data.model;

import java.time.Instant;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.cinema.data.archetype.Model;
import it.euris.academy.cinema.data.dto.MovieDto;
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
@Entity
@Table(name = "movie")
@SQLDelete(sql = "UPDATE Movie SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Movie implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "length")
  private Long length;
  
  @Column(name = "release_date")
  private Instant releaseDate;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;
  
  @OneToMany(mappedBy = "movie")
  @JsonIgnore
  private List<Screening> screenings;
  
  @Override
  public MovieDto toDto() {
    return MovieDto.builder()
        .id(UT.toString(id))
        .name(name)
        .length(UT.toString(length))
        .releaseDate(UT.toString(releaseDate))
        .build();
  }
}
