package pl.klubstrzelecki.serwer_klub_strzelecki.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String first_name;
    private String last_name;
    private String email;
    private String role;
    private String password;
    private UserDTO User;
    private List<UserDTO> userList;
}
