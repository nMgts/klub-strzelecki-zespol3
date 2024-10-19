package pl.klubstrzelecki.serwer_klub_strzelecki.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "weapons", schema = "public")
@Entity
public class Weapon {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    private String type;
    @Column(columnDefinition = "decimal")
    private double caliber;
    @Getter
    @Lob
    private byte[] image;

    public void setImage(byte[] image) {
        this.image = image;
    }
}
