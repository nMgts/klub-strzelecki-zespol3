package pl.klubstrzelecki.serwer_klub_strzelecki.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "competitions", schema = "public")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "Start_Date")
    private LocalDateTime startDate;
    @Column(name = "End_Date")
    private LocalDateTime endDate;
    @Column(name = "Shooters_Limit")
    private int shooters_limit;

    @ManyToMany(mappedBy = "competitions")
    private Set<User> users;
}
