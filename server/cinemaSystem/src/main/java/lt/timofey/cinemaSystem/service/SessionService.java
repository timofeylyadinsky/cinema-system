package lt.timofey.cinemaSystem.service;

import lt.timofey.cinemaSystem.entity.*;
import lt.timofey.cinemaSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SessionService {

    private SessionRepository sessionRepository;

    private HallRepository hallRepository;

    private SeatRepository seatRepository;

    private TicketRepository ticketRepository;

    private MovieRepository movieRepository;

    private UserRepository userRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository, HallRepository hallRepository, SeatRepository seatRepository, TicketRepository ticketRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    public List<Session> getAllSession() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(Long id) {
        return sessionRepository.getSessionsById(id);
    }

    public void makeNewSession() {
        movieRepository.save(new Movie("Pulp Fiction", "Quentin Tarantino Movie", "None", 5.0));
        movieRepository.save(new Movie("Kill Bill", "Quentin Tarantino Movie", "None", 4.5));
        movieRepository.save(new Movie("Snatch", "Gay Richie Movie", "None", 5.0));
        hallRepository.save(new Hall(10,10));
        hallRepository.save(new Hall(10,10));
        hallRepository.save(new Hall(15,15));
        for (int i = 1; i <= 15; i++) {
            for (int j = 1; j <=15; j++) {
                seatRepository.save(new Seat(i,j));
            }
        }
        sessionRepository.save(new Session(movieRepository.getMovieById(1L), hallRepository.getHallById(1L), LocalDate.now()));
        sessionRepository.save(new Session(movieRepository.getMovieById(1L), hallRepository.getHallById(2L), LocalDate.now()));
        sessionRepository.save(new Session(movieRepository.getMovieById(2L), hallRepository.getHallById(3L), LocalDate.now()));
        sessionRepository.save(new Session(movieRepository.getMovieById(3L), hallRepository.getHallById(2L), LocalDate.of(2023, 5, 7)));
        //ticketRepository.save(new Ticket(sessionRepository.getSessionsById(1L), seatRepository.getSeatByColumnNumAndAndRowNum(10,10), 123, userRepository.findUserById(1L)));
        Ticket ticket = new Ticket();
        ticket.setSession(sessionRepository.getSessionsById(1L));
        ticket.setSeat(seatRepository.getSeatByColumnNumAndAndRowNum(10,10));
        ticket.setPrice(123);
        ticket.setUser(userRepository.findUserById(1L));
        ticketRepository.save(ticket);
        //ticketRepository.save()
    }


}
