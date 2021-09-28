package it.euris.academy.cinema.data.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.cinema.data.archetype.Model;
import it.euris.academy.cinema.data.dto.HallDto;
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
@Table(name = "hall")
@SQLDelete(sql = "UPDATE Hall SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Hall implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "code")
  private String code;

  @Column(name = "number_of_seats")
  private Long numberOfSeats;

  @Column(name = "three_dimensional")
  @Builder.Default
  private Boolean threeDimensional = false;
  
  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;
  
  @ManyToOne
  @JoinColumn(name = "cinema_id", nullable = false)
  @JsonIgnore
  private Cinema cinema;
  
  @OneToMany(mappedBy = "hall")
  private List<Screening> screenings;
  
  @Override
  public HallDto toDto() {
    return HallDto.builder()
        .id(UT.toString(id))
        .code(code)
        .numberOfSeats(UT.toString(numberOfSeats))
        .threeDimensional(UT.toString(threeDimensional))
        .cinemaId(UT.toString(cinema.getId()))
        .build();
  }
}
