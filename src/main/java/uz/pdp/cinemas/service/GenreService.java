package uz.pdp.cinemas.service;

import org.springframework.stereotype.Service;
import uz.pdp.cinemas.entity.Genre;

import java.util.List;

@Service
public interface GenreService {
    Genre save(Genre genre);

    Genre update(Genre genre);

    void delete(Long id);

    Genre getById(Long id);

    Genre getByName(String name);

    List<Genre> getAll();
}
