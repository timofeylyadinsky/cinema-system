package lt.timofey.cinemaSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "hall")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private List<Session> session = new ArrayList<>();


    public Hall(int rowsOfSeat, int columnsOfSeat) {
        this.rowsOfSeat = rowsOfSeat;
        this.columnsOfSeat = columnsOfSeat;
    }

    public Hall() {}

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", rowsOfSeat=" + rowsOfSeat +
                ", columnsOfSeat=" + columnsOfSeat +
                '}';
    }
}
