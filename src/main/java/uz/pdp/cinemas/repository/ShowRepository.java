package uz.pdp.cinemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemas.entity.Show;

import java.util.List;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Long> {
    Optional<Show> findByName(String name);
    @Query("SELECT s FROM Show s WHERE s.movie.id = ?1")
    List<Show> findShowsByMovieId(Long id);
    @Query("SELECT s FROM Show s WHERE s.theater.id = ?1")
    List<Show> findShowsByTheaterId(Long id);
}

