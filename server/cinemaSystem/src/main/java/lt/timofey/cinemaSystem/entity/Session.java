package lt.timofey.cinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.ConstructorParameters;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "session")
    private List<Ticket> bookedTickets = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Hall hall;

    @Column(nullable = false)
    private LocalDate sessionDate;

    public Session(Movie movie, Hall hall, LocalDate sessionDate) {
        this.movie = movie;
        this.hall = hall;
        this.sessionDate = sessionDate;
    }
}
