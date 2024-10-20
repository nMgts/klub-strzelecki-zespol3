package pl.klubstrzelecki.serwer_klub_strzelecki.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "weapons", schema = "public")
@Entity
@Setter
@Getter
public class Weapon {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    private String type;

    @Column(columnDefinition = "decimal")
    private double caliber;

    @Lob
    private byte[] image;
}
