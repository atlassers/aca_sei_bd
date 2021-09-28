package it.euris.academy.cinema.data.model;

import java.util.List;
import java.util.ArrayList;
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
import it.euris.academy.cinema.data.dto.CinemaDto;
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
@Table(name = "cinema")
@SQLDelete(sql = "UPDATE Cinema SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Cinema implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;
  
  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;
  
  @OneToMany(mappedBy = "cinema")
  @Builder.Default
  @JsonIgnore
  List<Hall> halls = new ArrayList<Hall>();

  public Cinema(String id) {
    this.id = UT.toLong(id);
  }
  
  @Override
  public CinemaDto toDto() {
    return CinemaDto.builder()
        .id(UT.toString(id))
        .name(name)
        .address(address)
        .build();
  }
}
