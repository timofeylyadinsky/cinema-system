package lt.timofey.cinemaSystem.repository;

import lt.timofey.cinemaSystem.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    Hall getHallById(Long id);
}
