package it.euris.academy.cinema.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import it.euris.academy.cinema.data.model.Cinema;
import it.euris.academy.cinema.data.model.Movie;
import it.euris.academy.cinema.data.model.Screening;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-24
 */

public interface MovieRepository extends JpaRepository<Movie, Long> {

  @Query(value = ""
      + "SELECT s "
      + "FROM Screening s "
      + "WHERE s.movie.id = :id")
  List<Screening> getScreenings(@Param("id") Long id);
  
  @Query(value = ""
      + "SELECT c "
      + "FROM Cinema c "
      + "INNER JOIN Hall h "
      + "ON c.id = h.cinema.id "
      + "INNER JOIN Screening s "
      + "ON s.hall.id = h.id "
      + "WHERE s.movie.id = :id")
  List<Cinema> getCinemas(@Param("id") Long id);
}
