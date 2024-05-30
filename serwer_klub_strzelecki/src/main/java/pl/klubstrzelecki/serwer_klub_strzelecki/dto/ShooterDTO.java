package pl.klubstrzelecki.serwer_klub_strzelecki.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShooterDTO {
    private long id;
    private String first_name;
    private String last_name;
    private String email;
}
