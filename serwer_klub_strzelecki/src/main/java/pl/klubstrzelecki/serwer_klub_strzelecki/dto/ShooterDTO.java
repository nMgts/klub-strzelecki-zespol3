package pl.klubstrzelecki.serwer_klub_strzelecki.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShooterDTO {
    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private boolean assignedToUser;
}
