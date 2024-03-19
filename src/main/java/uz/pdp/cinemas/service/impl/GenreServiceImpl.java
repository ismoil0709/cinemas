package uz.pdp.cinemas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.cinemas.entity.Genre;
import uz.pdp.cinemas.exception.AlreadyExistsException;
import uz.pdp.cinemas.exception.NotFoundException;
import uz.pdp.cinemas.repository.GenreRepository;
import uz.pdp.cinemas.service.GenreService;
import uz.pdp.cinemas.util.Validations;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() != null) {
            if (genreRepository.findById(genre.getId()).isPresent())
                throw new AlreadyExistsException("Genre id");
        }
        if (genreRepository.findByName(genre.getName()).isPresent())
            throw new AlreadyExistsException("Genre name");

        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Genre genre) {
        Genre existingGenre = genreRepository.findById(genre.getId())
                .orElseThrow(() -> new NotFoundException("Genre"));

        Genre updatedGenre = Genre.builder()
                .id(existingGenre.getId())
                .name(Validations.requireNonNullElse(genre.getName(), existingGenre.getName()))
                .build();

        return genreRepository.save(updatedGenre);
    }

    @Override
    public void delete(Long id) {
        if (!genreRepository.existsById(id))
            throw new NotFoundException("Genre");

        genreRepository.deleteById(id);
    }

    @Override
    public Genre getById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre"));
    }

    @Override
    public Genre getByName(String name) {
        return genreRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Genre"));
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
}
