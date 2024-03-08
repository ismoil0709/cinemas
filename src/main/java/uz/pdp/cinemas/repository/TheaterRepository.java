package uz.pdp.cinemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemas.entity.Theater;

import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    Optional<Theater> findByName(String name);
}
