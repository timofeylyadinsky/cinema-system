package lt.timofey.cinemaSystem.service;

import lt.timofey.cinemaSystem.entity.Seat;
import lt.timofey.cinemaSystem.entity.Session;
import lt.timofey.cinemaSystem.entity.Ticket;
import lt.timofey.cinemaSystem.entity.User;
import lt.timofey.cinemaSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    public void bookTicket(Long sessionId, Long seat, User user) {
        Ticket ticket = new Ticket();
        ticket.setSeat(seatRepository.getSeatById(seat));
        ticket.setSession(sessionRepository.getSessionsById(sessionId));
        ticket.setPrice(150);
        ticket.setUser(user);
        ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketByUser(User user) {
        return ticketRepository.getTicketsByUser(user);
    }

    public void delete(Long id, User user) {
        ticketRepository.delete(ticketRepository.getTicketByIdAndUser(id, user));
    }
    /*public List<Ticket> getListOfAvailableTickets(Session session) {
        return
    }
    public Ticket getTicketToBook(int row, int column) {
        return ticketRepository.get;
    }*/
}
