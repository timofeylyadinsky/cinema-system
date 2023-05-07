package lt.timofey.cinemaSystem.service;

import jakarta.transaction.Transactional;
import lt.timofey.cinemaSystem.entity.Movie;
import lt.timofey.cinemaSystem.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void saveSomeMovie() {
        movieRepository.save(new Movie("Pulp Fiction", "Quentin Tarantino Movie", "None", 5.0));
        movieRepository.save(new Movie("Kill Bill", "Quentin Tarantino Movie", "None", 4.5));
        movieRepository.save(new Movie("Snatch", "Gay Richie Movie", "None", 5.0));
    }

    public Movie getMovieById(Long id) {
        return movieRepository.getMovieById(id);
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Transactional
    public void update(Long id, Movie updatedMovie) {
        updatedMovie.setId(id);
        movieRepository.save(updatedMovie);
    }

}
