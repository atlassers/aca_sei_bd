package it.euris.academy.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.euris.academy.cinema.data.model.Hall;

public interface HallRepository extends JpaRepository<Hall, Long> {

}
