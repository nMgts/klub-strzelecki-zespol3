package pl.klubstrzelecki.serwer_klub_strzelecki.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users", schema = "public")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    @Column(unique = true)
    private String email;
    private String password;
    private String roles;

    @ManyToMany
    @JoinTable(
            name = "user_competition",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "competition_id")
    )
    private Set<Competition> competitions;
}
