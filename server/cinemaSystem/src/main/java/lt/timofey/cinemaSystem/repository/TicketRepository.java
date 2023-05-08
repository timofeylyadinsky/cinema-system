package lt.timofey.cinemaSystem.repository;

import lt.timofey.cinemaSystem.entity.Seat;
import lt.timofey.cinemaSystem.entity.Session;
import lt.timofey.cinemaSystem.entity.Ticket;
import lt.timofey.cinemaSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket getTicketBySeat(Seat seat);
    List<Ticket> getTicketsBySession(Session s);

    List<Ticket> getTicketsByUser(User user);

    Ticket getTicketByIdAndUser(Long id, User user);
}
