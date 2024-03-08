package uz.pdp.cinemas.service;

import org.springframework.stereotype.Service;
import uz.pdp.cinemas.entity.Theater;

import java.util.List;

@Service
public interface TheaterService {
    Theater save(Theater theaterDto);
    Theater update(Theater theaterDto);
    void delete(Long id);
    Theater getById(Long id);
    Theater getByName(String name);
    List<Theater> getAll();
}
