package uz.pdp.cinemas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.cinemas.entity.Show;
import uz.pdp.cinemas.exception.AlreadyExistsException;
import uz.pdp.cinemas.exception.NotFoundException;
import uz.pdp.cinemas.repository.ShowRepository;
import uz.pdp.cinemas.service.ShowService;
import uz.pdp.cinemas.util.Validations;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;

    @Override
    public Show save(Show show) {
        if (show.getId() != null) {
            if (showRepository.findById(show.getId()).isPresent())
                throw new AlreadyExistsException("Show id");
        }
        if (showRepository.findByName(show.getName()).isPresent())
            throw new AlreadyExistsException("Show name");

        return showRepository.save(show);
    }

    @Override
    public Show update(Show show) {
        Show existingShow = showRepository.findById(show.getId())
                .orElseThrow(() -> new NotFoundException("Show"));

        Show updatedShow = Show.builder()
                .id(existingShow.getId())
                .name(Validations.requireNonNullElse(show.getName(), existingShow.getName()))
                .movie(Validations.requireNonNullElse(show.getMovie(), existingShow.getMovie()))
                .theater(Validations.requireNonNullElse(show.getTheater(), existingShow.getTheater()))
                .build();

        return showRepository.save(updatedShow);
    }

    @Override
    public void delete(Long id) {
        if (!showRepository.existsById(id))
            throw new NotFoundException("Show");

        showRepository.deleteById(id);
    }

    @Override
    public Show getById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Show"));
    }

    @Override
    public Show getByName(String name) {
        return showRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Show"));
    }

    @Override
    public List<Show> getALl() {
        return showRepository.findAll();
    }

    @Override
    public List<Show> getAllByMovie(Long id) {
        return showRepository.findShowsByMovieId(id);
    }

    @Override
    public List<Show> getAllByTheater(Long id) {
        return showRepository.findShowsByTheaterId(id);
    }
}
