package uz.pdp.cinemas.service;

import org.springframework.stereotype.Service;
import uz.pdp.cinemas.entity.Genre;
import uz.pdp.cinemas.entity.Movie;

import java.time.Duration;
import java.util.List;

@Service
public interface MovieService {
    Movie save(Movie movie);
    Movie update(Movie movie);
    void delete(Long id);
    Movie getById(Long id);
    Movie getByTitle(String title);
    List<Movie> getAll();
    List<Movie> getMoviesByGenres(List<Genre> genres);
    List<Movie> getMoviesByDuration(Duration duration);
}
