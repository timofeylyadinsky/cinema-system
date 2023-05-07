package lt.timofey.cinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private int rowNum;

    @Column(nullable = false)
    private int columnNum;


    public Seat(int rowNum, int columnNum) {
        this.columnNum = columnNum;
        this.rowNum = rowNum;
    }
}

