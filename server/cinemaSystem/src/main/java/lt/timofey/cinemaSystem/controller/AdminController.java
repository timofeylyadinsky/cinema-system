package lt.timofey.cinemaSystem.controller;

import jakarta.validation.Valid;
import lt.timofey.cinemaSystem.entity.Hall;
import lt.timofey.cinemaSystem.entity.Movie;
import lt.timofey.cinemaSystem.entity.Session;
import lt.timofey.cinemaSystem.entity.User;
import lt.timofey.cinemaSystem.payload.SessionDTO;
import lt.timofey.cinemaSystem.service.*;
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

    @Autowired
    private HallService hallService;

    @GetMapping()
    public String adminPage() {
        return "admin/admin";
    }


    //Movie Controllers
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



    //Hall controllers
    @GetMapping("/hall")
    public String getListOfHall(Model model) {
        model.addAttribute("hall", hallService.getAllHall());
        return "admin/hall/hall";
    }

    @GetMapping("/hall/add")
    public String addHallPage(@ModelAttribute("hall") Hall hall) {
        return "admin/hall/hall_add";
    }

    @PostMapping("/hall/add")
    public String addHall(@ModelAttribute("hall") @Valid Hall hall, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "admin/hall/hall_add";
        hallService.addHall(hall);
        return "redirect:/admin";
    }

    @GetMapping("/hall/{id}")
    public String getHallInfoById(Model model,@PathVariable("id") Long idHall) {
        model.addAttribute("hall", hallService.getHallById(idHall));
        return "admin/hall/hall_info";
    }

    @GetMapping("/hall/{id}/edit")
    public String editHall(Model model, @PathVariable("id") Long idHall) {
        model.addAttribute("hall", hallService.getHallById(idHall));
        return "admin/hall/hall_edit";
    }

    @PatchMapping("/hall/{id}/update")
    public String updateHall(@ModelAttribute("hall") @Valid Hall hall, BindingResult bindingResult, @PathVariable("id") Long idHall) {

        if(bindingResult.hasErrors()){
            return "admin/hall/hall_edit";
        }
        hallService.update(idHall, hall);
        return "redirect:/admin/hall";
    }

    //Session controller
    @GetMapping("/session")
    public String getListOfSessions(Model model) {
        model.addAttribute("sessions", sessionService.getAllSession());
        return "admin/session/session";
    }
    @GetMapping("/session/add")
    public String addSessionPage(@ModelAttribute("sessions") SessionDTO session, Model model) {
        model.addAttribute("hall", hallService.getAllHall());
        model.addAttribute("movie", movieService.getAllMovies());
        return "admin/session/session_add";
    }

    @PostMapping("/session/add")
    public String addHall(@ModelAttribute("sessions") @Valid SessionDTO session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "admin/session/session_add";
        sessionService.addSession(session);
        return "redirect:/admin";
    }

    @GetMapping("/session/{id}")
    public String getSessionInfoById(Model model,@PathVariable("id") Long idSession) {
        model.addAttribute("sessions", sessionService.getSessionById(idSession));
        return "admin/session/session_info";
    }

    @GetMapping("/session/{id}/edit")
    public String editSession(Model model, @PathVariable("id") Long idSession, @ModelAttribute("sessions") @Valid SessionDTO session, BindingResult bindingResult) {
//        model.addAttribute("sessions", new SessionDTO(
//                sessionService.getSessionById(idSession).getMovie().getId(),
//                sessionService.getSessionById(idSession).getHall().getId(),
//                sessionService.getSessionById(idSession).getSessionDate()));
        model.addAttribute("currentSessions", sessionService.getSessionById(idSession));
        model.addAttribute("hall", hallService.getAllHall());
        model.addAttribute("movie", movieService.getAllMovies());
        model.addAttribute("idS", idSession);
        return "admin/session/session_edit";
    }

    @PatchMapping("/session/{id}/update")
    public String updateSession(@ModelAttribute("sessions") @Valid SessionDTO session, BindingResult bindingResult, @PathVariable("id") Long idSession) {

        if(bindingResult.hasErrors()){
            return "admin/session/session_edit";
        }
        sessionService.update(session, idSession);
        return "redirect:/admin/session";
    }
}
