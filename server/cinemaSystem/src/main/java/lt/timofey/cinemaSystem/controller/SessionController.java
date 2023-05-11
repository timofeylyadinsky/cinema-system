package lt.timofey.cinemaSystem.controller;

import lt.timofey.cinemaSystem.entity.Seat;
import lt.timofey.cinemaSystem.payload.BookingTicket;
import lt.timofey.cinemaSystem.payload.DateDTO;
import lt.timofey.cinemaSystem.service.SessionService;
import lt.timofey.cinemaSystem.service.TicketService;
import lt.timofey.cinemaSystem.service.UserDetailsImpl;
import lt.timofey.cinemaSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@Controller
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

//    @GetMapping("/session/addNew")
//    public void makeSession() {
//        sessionService.makeNewSession();
//    }

    @GetMapping("/session")
    public String showAllSession(Model model){
        model.addAttribute("sessions", sessionService.getAllSession());
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("searchDate", new DateDTO());
        System.out.println(LocalDate.now().isEqual(sessionService.getAllSession().get(0).getSessionDate()));
        return "session/session";
    }

    @PostMapping("/session")
    public String findByDate(Model model, @ModelAttribute("searchDate") DateDTO dateDTO){
        model.addAttribute("sessions", dateDTO.getLocalDate()!=null ? sessionService.getSessionByDate(dateDTO.getLocalDate()): sessionService.getAllSession());
        model.addAttribute("currentDate", LocalDate.now());
        //model.addAttribute("searchDate", new DateDTO());
        //System.out.println(LocalDate.now().isEqual(sessionService.getAllSession().get(0).getSessionDate()));
        return "session/session";
    }


    @GetMapping("/session/{id}")
    public String getMovieInfoById(Model model,@PathVariable("id") Long idSession, @ModelAttribute("seating") BookingTicket bookingTicket) {
        model.addAttribute("sessions", sessionService.getSessionById(idSession));
        model.addAttribute("seats", sessionService.getAvailableSeats(sessionService.getSessionById(idSession)));
        //model.addAttribute("seating", );
        return "session/session_info";
    }
    /*@RequestParam(name = "sessionId") Long sessionId, @RequestParam(name  = "seat") Long seat, */
    @PostMapping("/ticket/booking")
    public String bookedSeat(@AuthenticationPrincipal UserDetailsImpl userDetails, @ModelAttribute("seating") BookingTicket bookingTicket, BindingResult bindingResult) {
        System.out.println(bookingTicket.toString());
        ticketService.bookTicket(bookingTicket.getSessionId(), bookingTicket.getSeat(), userDetails.getUser());
        return "redirect:/profile";
    }

}
