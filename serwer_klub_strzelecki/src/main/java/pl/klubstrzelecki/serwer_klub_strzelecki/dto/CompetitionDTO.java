package pl.klubstrzelecki.serwer_klub_strzelecki.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompetitionDTO {
    private long id;
    private String name;
    private String description;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private int shooters_limit;
    private List<String> emails;
}
