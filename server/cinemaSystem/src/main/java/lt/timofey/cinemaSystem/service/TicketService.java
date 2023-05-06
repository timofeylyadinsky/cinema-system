package lt.timofey.cinemaSystem.service;

import lt.timofey.cinemaSystem.entity.Session;
import lt.timofey.cinemaSystem.entity.Ticket;
import lt.timofey.cinemaSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TicketService {
    private SessionRepository sessionRepository;

    private HallRepository hallRepository;

    private SeatRepository seatRepository;

    private TicketRepository ticketRepository;

    private MovieRepository movieRepository;

    private UserRepository userRepository;

    @Autowired
    public TicketService(SessionRepository sessionRepository, HallRepository hallRepository, SeatRepository seatRepository, TicketRepository ticketRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }


    /*public List<Ticket> getListOfAvailableTickets(Session session) {
        return
    }

    public Ticket getTicketToBook(int row, int column) {
        return ticketRepository.get;
    }*/
}
