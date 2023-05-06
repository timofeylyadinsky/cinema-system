package lt.timofey.cinemaSystem.service;

import lt.timofey.cinemaSystem.entity.Seat;
import lt.timofey.cinemaSystem.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {
    private SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository s) {
        this.seatRepository = s;
    }


}
