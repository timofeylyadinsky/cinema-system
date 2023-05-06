package lt.timofey.cinemaSystem.controller;

import lt.timofey.cinemaSystem.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping("/session/addNew")
    public void makeSession() {
        sessionService.makeNewSession();
    }

    @GetMapping("/session")
    public String showAllSession(Model model){
        model.addAttribute("sessions", sessionService.getAllSession());
        return "session/session";
    }


    @GetMapping("/session/{id}")
    public String getMovieInfoById(Model model,@PathVariable("id") Long idSession) {
        model.addAttribute("sessions", sessionService.getSessionById(idSession));
        model.addAttribute("seats", sessionService.getAvailableSeats(sessionService.getSessionById(idSession)));
        return "session/session_info";
    }

}
