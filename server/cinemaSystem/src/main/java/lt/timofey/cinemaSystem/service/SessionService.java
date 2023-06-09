package lt.timofey.cinemaSystem.service;

import lt.timofey.cinemaSystem.entity.*;
import lt.timofey.cinemaSystem.exception.SessionExistException;
import lt.timofey.cinemaSystem.payload.SessionDTO;
import lt.timofey.cinemaSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SessionService {

    private SessionRepository sessionRepository;

    private HallRepository hallRepository;

    private SeatRepository seatRepository;

    private TicketRepository ticketRepository;

    private MovieRepository movieRepository;

    private UserRepository userRepository;

    private SeatService seatService;

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

    public void addSession(SessionDTO session) {
        if (sessionRepository.getSessionsBySessionDateAndHall(session.getLocalDate(), hallRepository.getHallById(session.getHallId()))!=null)
        {
            throw new SessionExistException("Not available parameter");
        } else {
            Session session1 = new Session();
            session1.setMovie(movieRepository.getMovieById(session.getMovieId()));
            session1.setHall(hallRepository.getHallById(session.getHallId()));
            session1.setSessionDate(session.getLocalDate());
            sessionRepository.save(session1);
        }
    }
    public List<Seat> getAvailableSeats(Session session) {
        List<Seat> availableSeats = getSeats(session.getHall().getRowsOfSeat(),session.getHall().getColumnsOfSeat());
        List<Seat> bookedSeats = getBookedSeats(session);
        for(Seat s : bookedSeats) {
            availableSeats.remove(s);
        }
        System.out.println(availableSeats.toString());
        return availableSeats;
    }

    public void update(SessionDTO sessionDTO, Long id) {
        if (sessionRepository.getSessionsBySessionDateAndHall(sessionDTO.getLocalDate(), hallRepository.getHallById(sessionDTO.getHallId()))!=null)
        {
            throw new SessionExistException("Not available parameter");
        } else {
            Session session = sessionRepository.getSessionsById(id);
            session.setSessionDate(sessionDTO.getLocalDate());
            session.setMovie(movieRepository.getMovieById(sessionDTO.getMovieId()));
            session.setHall(hallRepository.getHallById(sessionDTO.getHallId()));
            sessionRepository.save(session);
        }
    }

    public List<Session> getSessionByMovie(Movie movie) {
        return sessionRepository.getSessionsByMovie(movie);
    }

    public List<Seat> getBookedSeats(Session sessionId) {
        List<Ticket> tickets = ticketRepository.getTicketsBySession(sessionId);
        List<Seat> seats = new ArrayList<>();
        for(Ticket i : tickets) {
            seats.add(i.getSeat());
        }
        return seats;
    }

    public List<Seat> getSeats(int rowNum, int columnNum) {
        List<Seat> seats = new ArrayList<>();
        for(int i = 1; i <= columnNum; i++) {
            for(int j = 1; j <= rowNum; j++) {
                seats.add(seatRepository.getSeatByColumnNumAndRowNum(i,j));
            }
        }
        return seats;
    }

    public List<Session> getSessionByDate(LocalDate localeDate) {
        return sessionRepository.getSessionsBySessionDate(localeDate);
    }

    public void delete(Long id) {
        sessionRepository.delete(sessionRepository.getSessionsById(id));
    }
}


//    public void makeNewSession() {
//        movieRepository.save(new Movie("Pulp Fiction", "Quentin Tarantino Movie", "None", 5.0));
//        movieRepository.save(new Movie("Kill Bill", "Quentin Tarantino Movie", "None", 4.5));
//        movieRepository.save(new Movie("Snatch", "Gay Richie Movie", "None", 5.0));
//        hallRepository.save(new Hall(10,10));
//        hallRepository.save(new Hall(10,10));
//        hallRepository.save(new Hall(15,15));
//        for (int i = 1; i <= 15; i++) {
//            for (int j = 1; j <=15; j++) {
//                seatRepository.save(new Seat(i,j));
//            }
//        }
//        sessionRepository.save(new Session(movieRepository.getMovieById(1L), hallRepository.getHallById(1L), LocalDate.now()));
//        sessionRepository.save(new Session(movieRepository.getMovieById(1L), hallRepository.getHallById(2L), LocalDate.now()));
//        sessionRepository.save(new Session(movieRepository.getMovieById(2L), hallRepository.getHallById(3L), LocalDate.now()));
//        sessionRepository.save(new Session(movieRepository.getMovieById(3L), hallRepository.getHallById(2L), LocalDate.of(2023, 5, 7)));
//        Ticket ticket = new Ticket();
//        ticket.setSession(sessionRepository.getSessionsById(1L));
//        ticket.setSeat(seatRepository.getSeatByColumnNumAndRowNum(10,10));
//        ticket.setPrice(123);
//        ticket.setSeat(seatRepository.getSeatByColumnNumAndRowNum(1,2));
//        ticketRepository.save(ticket);
//        System.out.println(Arrays.toString(sessionRepository.getSessionsById(1L).getBookedTickets().toArray()));
//    }
