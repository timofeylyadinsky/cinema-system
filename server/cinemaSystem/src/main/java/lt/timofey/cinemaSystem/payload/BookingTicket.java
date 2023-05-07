package lt.timofey.cinemaSystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.timofey.cinemaSystem.entity.Seat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingTicket {
    private Long sessionId;
    private Long seat;

}
