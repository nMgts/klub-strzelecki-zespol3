package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ReqRes;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ShooterRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.UserRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.util.JWTUtils;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserManagementService {
    private final UserRepository userRepository;
    private final ShooterRepository shooterRepository;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserManagementService(UserRepository userRepository, JWTUtils jwtUtils,
                                 AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                                 ShooterRepository shooterRepository) {
        this.userRepository = userRepository;
        this.shooterRepository = shooterRepository;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    //@Transactional
    public ReqRes register(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();

        try {
            Shooter shooter = new Shooter();
            shooter.setFirst_name(registrationRequest.getFirst_name());
            shooter.setLast_name(registrationRequest.getLast_name());
            shooter.setEmail(registrationRequest.getEmail());
            Shooter shooterResult = shooterRepository.save(shooter);

            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setFirst_name(registrationRequest.getFirst_name());
            user.setLast_name(registrationRequest.getLast_name());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setShooter(shooterResult);
            user.setRole("USER");
            User userResult = userRepository.save(user);
            if (userResult.getId() > 0 || shooterResult.getId() > 0) {
                resp.setUser(userResult);
                resp.setMessage("User saved successfully");
                resp.setStatusCode(200);
            }

        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes login(ReqRes loginRequest) {
        ReqRes response = new ReqRes();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully logged in");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest) {
        ReqRes response = new ReqRes();
        try {
            String email = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            User user = userRepository.findByEmail(email).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)) {
                var jwt = jwtUtils.generateToken(user);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully refreshed token");
            }
            response.setStatusCode(200);
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
