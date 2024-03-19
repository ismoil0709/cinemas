package uz.pdp.cinemas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.cinemas.entity.Genre;
import uz.pdp.cinemas.entity.Movie;
import uz.pdp.cinemas.exception.AlreadyExistsException;
import uz.pdp.cinemas.exception.InvalidArgumentException;
import uz.pdp.cinemas.exception.NotFoundException;
import uz.pdp.cinemas.repository.MovieRepository;
import uz.pdp.cinemas.service.MovieService;
import uz.pdp.cinemas.util.Validations;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    @Override
    public Movie save(Movie movie) {
        if (movie.getId() != null) {
            if (movieRepository.findById(movie.getId()).isPresent())
                throw new AlreadyExistsException("Movie id");
        }
        if (movieRepository.findByTitle(movie.getTitle()).isPresent())
            throw new AlreadyExistsException("Movie title");

        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Movie movie) {
        Movie existingMovie = movieRepository.findById(movie.getId())
                .orElseThrow(()->new NotFoundException("Movie"));

        Movie updatedMovie = Movie.builder()
                .id(existingMovie.getId())
                .title(Validations.requireNonNullElse(movie.getTitle(), existingMovie.getTitle()))
                .genres(Validations.requireNonNullElse(movie.getGenres(), existingMovie.getGenres()))
                .duration(Validations.requireNonNullElse(movie.getDuration(), existingMovie.getDuration()))
                .imgPath(Validations.requireNonNullElse(movie.getImgPath(), existingMovie.getImgPath()))
                .build();

        return movieRepository.save(updatedMovie);
    }

    @Override
    public void delete(Long id) {
        if (!movieRepository.existsById(id))
            throw new NotFoundException("Movie");

        movieRepository.deleteById(id);
    }

    @Override
    public Movie getById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie"));
    }

    @Override
    public Movie getByTitle(String title) {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Movie"));
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByGenres(List<Genre> genres) {
        return movieRepository.findMoviesByGenres(genres);
    }

    @Override
    public List<Movie> getMoviesByDuration(Duration duration) {
        if (duration == null)
            throw new InvalidArgumentException("Duration");
        return movieRepository.findMoviesByDuration(duration);
    }
}
