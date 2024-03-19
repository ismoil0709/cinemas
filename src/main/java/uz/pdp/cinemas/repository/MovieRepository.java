package uz.pdp.cinemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemas.entity.Genre;
import uz.pdp.cinemas.entity.Movie;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
    List<Movie> findMoviesByGenres(List<Genre> genres);
    List<Movie> findMoviesByDuration(Duration duration);
}
