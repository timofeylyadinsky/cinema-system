package lt.timofey.cinemaSystem.controller;

import lt.timofey.cinemaSystem.service.MovieService;
import lt.timofey.cinemaSystem.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/movie")
    public String getListOfMovie(Model model) {
        model.addAttribute("movie", movieService.getAllMovies());
        return "movie/movie";
    }

//    @GetMapping("/movie/add")
//    public void addMovie() {
//        movieService.saveSomeMovie();
//    }

    @GetMapping("/movie/{id}")
    public String getMovieInfoById(Model model,@PathVariable("id") Long idFilm) {
        model.addAttribute("movie", movieService.getMovieById(idFilm));
        model.addAttribute("sessions", sessionService.getSessionByMovie(movieService.getMovieById(idFilm)));
        return "movie/movie_info";
    }

}
