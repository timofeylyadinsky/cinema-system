package lt.timofey.cinemaSystem.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column
    private String poster;

    @Column
    private Double rate;
}
