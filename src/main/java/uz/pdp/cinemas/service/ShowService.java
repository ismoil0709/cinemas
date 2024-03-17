package uz.pdp.cinemas.service;

import org.springframework.stereotype.Service;
import uz.pdp.cinemas.entity.Show;

import java.util.List;

@Service

public interface ShowService {
    Show save(Show show);
    Show update(Show show);
    void delete(Long id);
    Show getById(Long id);
    Show getByName(String name);
    List<Show> getALl();
    List<Show> getAllByMovie(Long id);
    List<Show> getAllByTheater(Long id);
}
