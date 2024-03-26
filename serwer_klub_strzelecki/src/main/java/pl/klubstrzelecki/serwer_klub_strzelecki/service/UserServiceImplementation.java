package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("User not found with id" + userId);
    }
}
