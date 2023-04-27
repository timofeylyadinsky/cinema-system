package lt.timofey.cinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private int rowsOfSeat;

    @Column(nullable = false)
    private int columnsOfSeat;
}
