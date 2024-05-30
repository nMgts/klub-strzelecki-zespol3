package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.klubstrzelecki.serwer_klub_strzelecki.dto.UserDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("Shooter not found with id" + userId);
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getFirst_name(),
                savedUser.getLast_name(), savedUser.getEmail(), savedUser.getPassword(),
                savedUser.getRoles());
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> opt = getUserById(id);
        if (opt.isPresent()) {
            User user = opt.get();
            userRepository.delete(user);
        }
        else {
            //todo
        }
    }

    private Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
