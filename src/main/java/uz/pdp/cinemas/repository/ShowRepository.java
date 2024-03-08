package uz.pdp.cinemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemas.entity.Show;

import java.util.List;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Long> {
    Optional<Show> findByName(String name);
    List<Show> findShowsByMovieId(Long id);
    List<Show> findShowsByTheaterId(Long id);
}

