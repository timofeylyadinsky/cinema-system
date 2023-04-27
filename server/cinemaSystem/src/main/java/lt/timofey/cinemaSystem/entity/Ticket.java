package lt.timofey.cinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Session session;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Seat seat;

    @Column(nullable = false)
    private Integer price;
}
