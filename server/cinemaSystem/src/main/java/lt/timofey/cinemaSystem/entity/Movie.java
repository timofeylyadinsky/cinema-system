package lt.timofey.cinemaSystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

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


    @OneToMany(cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "movie")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private List<Session> session = new ArrayList<>();

    public Movie(String name, String title, String poster, Double rate) {
        this.name = name;
        this.title = title;
        this.poster = poster;
        this.rate = rate;
    }

    public Movie() {
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", rate=" + rate +
                '}';
    }
}
