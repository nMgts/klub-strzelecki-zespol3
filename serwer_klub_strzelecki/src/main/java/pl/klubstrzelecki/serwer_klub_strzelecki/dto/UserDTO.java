package pl.klubstrzelecki.serwer_klub_strzelecki.dto;

public record UserDTO(
        long id,
        String first_name,
        String last_name,
        String email,
        String password,
        String roles
) {}
