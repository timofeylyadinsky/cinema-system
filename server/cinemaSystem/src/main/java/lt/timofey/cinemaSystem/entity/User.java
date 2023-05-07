package lt.timofey.cinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lt.timofey.cinemaSystem.entity.enums.ERole;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length=100)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<Ticket> tickets = new ArrayList<>();


    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private ERole roles;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;
}
