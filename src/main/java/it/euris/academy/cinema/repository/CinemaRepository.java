package it.euris.academy.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.euris.academy.cinema.data.model.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

}
