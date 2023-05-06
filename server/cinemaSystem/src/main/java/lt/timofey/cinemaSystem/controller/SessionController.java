package lt.timofey.cinemaSystem.controller;

import lt.timofey.cinemaSystem.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping("/session/addNew")
    public void makeSession() {
        sessionService.makeNewSession();
    }

}
