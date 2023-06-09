package lt.timofey.cinemaSystem.repository;

import lt.timofey.cinemaSystem.entity.Hall;
import lt.timofey.cinemaSystem.entity.Movie;
import lt.timofey.cinemaSystem.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session getSessionsById(Long id);

    List<Session> getSessionsBySessionDate(LocalDate sessionDate);

    Session getSessionsBySessionDateAndHall(LocalDate sessionDate, Hall hall);

    List<Session> getSessionsByMovie(Movie movie);
}
