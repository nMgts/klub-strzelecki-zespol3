package pl.klubstrzelecki.serwer_klub_strzelecki.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "news", schema = "public")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Content")
    private String content;
}
