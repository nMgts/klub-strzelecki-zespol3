package pl.klubstrzelecki.serwer_klub_strzelecki.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shooters", schema = "public")
public class Shooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String first_name;
    private String last_name;
    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "shooter_competition",
        joinColumns = @JoinColumn(name = "shooter_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "competition_id", referencedColumnName = "id")
    )
    private Set<Competition> competitions = new HashSet<>();
}
