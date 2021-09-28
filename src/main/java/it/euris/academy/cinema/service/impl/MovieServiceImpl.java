package it.euris.academy.cinema.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.euris.academy.cinema.data.dto.CinemaDto;
import it.euris.academy.cinema.data.dto.MovieDto;
import it.euris.academy.cinema.data.dto.ScreeningDto;
import it.euris.academy.cinema.data.model.Cinema;
import it.euris.academy.cinema.data.model.Movie;
import it.euris.academy.cinema.data.model.Screening;
import it.euris.academy.cinema.exception.IdMustBeNullException;
import it.euris.academy.cinema.exception.IdMustNotBeNullException;
import it.euris.academy.cinema.repository.MovieRepository;
import it.euris.academy.cinema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

  @Autowired
  MovieRepository movieRepository;

  @Override
  public List<MovieDto> getAll() {
    return movieRepository.findAll()
        .stream().map(Movie::toDto).collect(Collectors.toList());
  }

  @Override
  public MovieDto get(Long id) {
    return movieRepository.findById(id).map(Movie::toDto).orElse(null);
  }

  @Override
  public MovieDto add(MovieDto movieDto) {
    if (movieDto.getId() != null)
      throw new IdMustBeNullException();

    return movieRepository.save(movieDto.toModel()).toDto();
  }

  @Override
  public MovieDto update(MovieDto movieDto) {
    if (movieDto.getId() == null)
      throw new IdMustNotBeNullException();

    return movieRepository.save(movieDto.toModel()).toDto();
  }

  @Override
  public Boolean delete(Long id) {
    movieRepository.deleteById(id);
    return movieRepository.findById(id).isEmpty();
  }
  
  @Override
  public List<ScreeningDto> getScreenings(Long id){
    return movieRepository.getScreenings(id)
        .stream().map(Screening::toDto).collect(Collectors.toList());
  }

  @Override
  public List<CinemaDto> getCinemas(Long id){
    return movieRepository.getCinemas(id)
        .stream().map(Cinema::toDto).collect(Collectors.toList());
  }
}
