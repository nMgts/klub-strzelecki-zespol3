package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.klubstrzelecki.serwer_klub_strzelecki.convert.UserDTOMapper;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ReqRes;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.UserDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.UserRepository;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public ReqRes getAllUsers() {
        ReqRes reqRes = new ReqRes();

        try {
            List<User> result = userRepository.findAll();
            if (!result.isEmpty()) {

                List<UserDTO> userDTOList = new ArrayList<>();
                for (User user : result) {
                    userDTOList.add(userDTOMapper.convertUserToUserDTO(user));
                }

                reqRes.setUserList(userDTOList);
                reqRes.setStatusCode(200);
                reqRes.setMessage("successfull");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occured: " + e.getMessage());
            return reqRes;
        }
    }

    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(userDTOMapper.convertUserToUserDTO(user));
        }
        return userDTOList;
    }

    public UserDTO findUserById(long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);

        if (opt.isPresent()) {
            User user = opt.get();
            return userDTOMapper.convertUserToUserDTO(user);
        }
        throw new Exception("User not found with id" + userId);
    }

    public User saveUser(UserDTO userDTO) {
        User user = userDTOMapper.convertUserDTOtoUser(userDTO);
        return userRepository.save(user);
    }

    public User updateUser(long id, UserDTO userDTO) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new Exception("User not found with id " + id);
        }

        User existingUser = userOptional.get();
        User updatedUser = userDTOMapper.convertUserDTOtoUser(userDTO);
        updatedUser.setPassword(existingUser.getPassword());
        updatedUser.setId(existingUser.getId());

        return userRepository.save(updatedUser);
    }

    public void deleteUserById(long id) throws Exception {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            User user = opt.get();
            userRepository.delete(user);
        } else {
            throw new Exception("User not found with id " + id);
        }
    }

    public ReqRes getMyInfo(String email) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (userOpt.isPresent()) {
                UserDTO userDTO = userDTOMapper.convertUserToUserDTO(userOpt.get());
                reqRes.setUser(userDTO);
                reqRes.setStatusCode(200);
                reqRes.setMessage("successfull");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occured while getting user info: " + e.getMessage());
        }
        return reqRes;
    }
}
