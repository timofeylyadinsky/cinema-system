package lt.timofey.cinemaSystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {
    private Long movieId;
    private Long hallId;
    private LocalDate localDate;
}
