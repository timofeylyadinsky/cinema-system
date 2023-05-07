package lt.timofey.cinemaSystem.service;

import lt.timofey.cinemaSystem.entity.Hall;
import lt.timofey.cinemaSystem.entity.Seat;
import lt.timofey.cinemaSystem.repository.HallRepository;
import lt.timofey.cinemaSystem.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {
    private final HallRepository hallRepository;

    private final SeatRepository seatRepository;

    @Autowired
    public HallService(HallRepository hallRepository, SeatRepository seatRepository) {
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
    }

    public List<Hall> getAllHall() {
        return hallRepository.findAll();
    }

    public void addHall(Hall hall) {
        hallRepository.save(hall);
        for (int i = 1; i <= hall.getRowsOfSeat(); i++) {
            for (int j = 1; j <= hall.getColumnsOfSeat(); j++) {
                if (seatRepository.getSeatByColumnNumAndRowNum(j, i) == null) seatRepository.save(new Seat(i,j));
            }
        }
    }

    public Hall getHallById (Long id) {
        return hallRepository.getHallById(id);
    }


    public void update (Long id, Hall updatedHall) {
        updatedHall.setId(id);
        for (int i = 1; i <= updatedHall.getRowsOfSeat(); i++) {
            for (int j = 1; j <= updatedHall.getColumnsOfSeat(); j++) {
                if (seatRepository.getSeatByColumnNumAndRowNum(j, i) == null) seatRepository.save(new Seat(i,j));
            }
        }
        hallRepository.save(updatedHall);
    }
}
