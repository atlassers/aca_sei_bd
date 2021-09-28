package it.euris.academy.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.euris.academy.cinema.data.model.Screening;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-27
 */

public interface ScreeningRepository extends JpaRepository<Screening, Long> {

}
