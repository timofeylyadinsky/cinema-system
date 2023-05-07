package lt.timofey.cinemaSystem.repository;

import lt.timofey.cinemaSystem.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Seat getSeatByColumnNumAndRowNum(int column, int Row);
    Seat getSeatById(Long id);
}
