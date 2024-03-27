package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.UserRepository;

@RestController
@RequestMapping
public class ApiController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String goHome() {
        return "This is publickly accesible withing needing authentication";
    }

}
