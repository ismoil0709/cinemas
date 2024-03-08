package uz.pdp.cinemas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.cinemas.entity.Theater;
import uz.pdp.cinemas.exception.AlreadyExistsException;
import uz.pdp.cinemas.exception.NotFoundException;
import uz.pdp.cinemas.repo.TheaterRepository;
import uz.pdp.cinemas.service.TheaterService;
import uz.pdp.cinemas.util.Validations;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterImpl implements TheaterService {
    private final TheaterRepository theaterRepository;

    @Override
    public Theater save(Theater theater) {
        if (theater.getId() != null) {
            if (theaterRepository.findById(theater.getId()).isPresent())
                throw new AlreadyExistsException("Theater id");
        }
        if (theaterRepository.findByName(theater.getName()).isPresent())
            throw new AlreadyExistsException("Theater name");

        return theaterRepository.save(theater);
    }

    @Override
    public Theater update(Theater theater) {
        Theater existingTheater = theaterRepository.findById(theater.getId())
                .orElseThrow(() -> new NotFoundException("Theater"));

        Theater updatedTheater = Theater.builder()
                .id(existingTheater.getId())
                .name(Validations.requireNonNullElse(theater.getName(), existingTheater.getName()))
                .location(Validations.requireNonNullElse(theater.getLocation(), existingTheater.getLocation()))
                .build();

        return theaterRepository.save(updatedTheater);
    }

    @Override
    public void delete(Long id) {
        if (!theaterRepository.existsById(id))
            throw new NotFoundException("Theater");

        theaterRepository.deleteById(id);
    }

    @Override
    public Theater getById(Long id) {
        return theaterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Theater"));
    }

    @Override
    public Theater getByName(String name) {
        return theaterRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Theater"));
    }

    @Override
    public List<Theater> getAll() {
        return theaterRepository.findAll();
    }
}
