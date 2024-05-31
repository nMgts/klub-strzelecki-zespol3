package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ShooterDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.UserDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findUserById(long id) throws Exception;

    void deleteUserById(long id) throws Exception;
    User saveUser(UserDTO userDTO);
    User updateUser(long id, UserDTO userDTO) throws Exception;
}
