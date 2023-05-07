package lt.timofey.cinemaSystem.controller;

import jakarta.validation.Valid;
import lt.timofey.cinemaSystem.entity.Movie;
import lt.timofey.cinemaSystem.entity.User;
import lt.timofey.cinemaSystem.service.MovieService;
import lt.timofey.cinemaSystem.service.SeatService;
import lt.timofey.cinemaSystem.service.SessionService;
import lt.timofey.cinemaSystem.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SeatService seatService;

    @GetMapping()
    public String adminPage() {
        return "admin/admin";
    }

    @GetMapping("/movie/add")
    public String addMoviePage(@ModelAttribute("movie") Movie movie) {
        return "admin/movie/movie_add";
    }

    @PostMapping("/movie/add")
    public String addMovie(@ModelAttribute("movie") Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/admin";
    }

    @GetMapping("/movie")
    public String getListOfMovie(Model model) {
        model.addAttribute("movie", movieService.getAllMovies());
        return "admin/movie/movie";
    }

    @GetMapping("/movie/{id}")
    public String getMovieInfoById(Model model,@PathVariable("id") Long idFilm) {
        model.addAttribute("movie", movieService.getMovieById(idFilm));
        return "admin/movie/movie_info";
    }

    @GetMapping("/movie/{id}/edit")
    public String editMovie(Model model,@PathVariable("id") Long idFilm) {
        model.addAttribute("movie", movieService.getMovieById(idFilm));
        return "admin/movie/movie_edit";
    }

    @PatchMapping("/movie/{id}/update")
    public String updateMovie(@ModelAttribute("movie") Movie movie, BindingResult bindingResult, @PathVariable("id") Long idFilm) {

        if(bindingResult.hasErrors()){
            return "redirect:/movie/{id}/edit";
        }

        movieService.update(movie.getId(), movie);

        return "redirect:/admin/movie";
    }

}
