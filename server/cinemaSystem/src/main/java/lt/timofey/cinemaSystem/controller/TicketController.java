package lt.timofey.cinemaSystem.controller;

import lt.timofey.cinemaSystem.service.TicketService;
import lt.timofey.cinemaSystem.service.UserDetailsImpl;
import lt.timofey.cinemaSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/profile/ticket/{id}/delete")
    public String deleteTicketFromUser(@PathVariable("id") Long ticketId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ticketService.delete(ticketId, userDetails.getUser());
        return "redirect:/profile";
    }
}
