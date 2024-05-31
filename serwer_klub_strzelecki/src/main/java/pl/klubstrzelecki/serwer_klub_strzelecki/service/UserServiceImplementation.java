package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.klubstrzelecki.serwer_klub_strzelecki.convert.UserDTOMapper;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.UserDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user: userList) {
            userDTOList.add(userDTOMapper.convertUserToUserDTO(user));
        }
        return userDTOList;
    }

    @Override
    public UserDTO findUserById(long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);

        if (opt.isPresent()) {
            User user = opt.get();
            return userDTOMapper.convertUserToUserDTO(user);
        }
        throw new Exception("User not found with id" + userId);
    }

    @Override
    public User saveUser(UserDTO userDTO) {
        User user = userDTOMapper.convertUserDTOtoUser(userDTO);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(long id, UserDTO userDTO) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new Exception("User not found with id " + id);
        }

        User existingUser = userOptional.get();
        User updatedUser = userDTOMapper.convertUserDTOtoUser(userDTO);
        updatedUser.setId(existingUser.getId());

        return userRepository.save(updatedUser);
    }

    @Override
    public void deleteUserById(long id) throws Exception {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            User user = opt.get();
            userRepository.delete(user);
        }
        else {
            throw new Exception("User not found with id " + id);
        }
    }
}
