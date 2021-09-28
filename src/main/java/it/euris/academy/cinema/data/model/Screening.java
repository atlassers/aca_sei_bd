package it.euris.academy.cinema.data.model;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import it.euris.academy.cinema.data.archetype.Model;
import it.euris.academy.cinema.data.dto.ScreeningDto;
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
@Entity
@Table(name = "screening")
@SQLDelete(sql = "UPDATE Screening SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Screening implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  @Column(name = "date")
  private Instant date;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;

  @ManyToOne
  @JoinColumn(name = "hall_id", nullable = false)
  private Hall hall;
  
  @ManyToOne
  @JoinColumn(name = "movie_id", nullable = false)
  private Movie movie;
  
  @Override
  public ScreeningDto toDto() {
    return ScreeningDto.builder()
        .hallId(hall.getId().toString())
        .movieId(movie.getId().toString())
        .date(UT.toString(date))
        .build();
  }
}
