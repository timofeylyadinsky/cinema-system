package lt.timofey.cinemaSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Max(30)
    @Min(1)
    @Column(nullable = false)
    private int rowsOfSeat;

    @Column(nullable = false)
    @Max(30)
    @Min(1)
    private int columnsOfSeat;

    public Hall(int rowsOfSeat, int columnsOfSeat) {
        this.rowsOfSeat = rowsOfSeat;
        this.columnsOfSeat = columnsOfSeat;
    }

    public Hall() {}
}
