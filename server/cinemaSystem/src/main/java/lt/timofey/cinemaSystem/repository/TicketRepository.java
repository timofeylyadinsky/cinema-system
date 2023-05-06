package lt.timofey.cinemaSystem.repository;

import lt.timofey.cinemaSystem.entity.Seat;
import lt.timofey.cinemaSystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket getTicketBySeat(Seat seat);
}
