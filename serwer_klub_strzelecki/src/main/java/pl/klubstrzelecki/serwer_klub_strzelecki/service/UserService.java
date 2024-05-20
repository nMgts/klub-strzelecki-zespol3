package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ShooterDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.UserDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;

public interface UserService {

    User findUserById(long userId) throws Exception;
    void deleteUserById(Long id);
    UserDTO saveUser(UserDTO user);
}
