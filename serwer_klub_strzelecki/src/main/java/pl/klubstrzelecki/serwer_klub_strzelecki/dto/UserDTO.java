package pl.klubstrzelecki.serwer_klub_strzelecki.dto;

import jakarta.persistence.Column;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String first_name;
    private String last_name;
    @Column(unique = true)
    private String email;
    private String password;
    private String roles;
}
