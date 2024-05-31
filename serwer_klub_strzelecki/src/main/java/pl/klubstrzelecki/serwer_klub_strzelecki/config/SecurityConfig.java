package pl.klubstrzelecki.serwer_klub_strzelecki.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.UserRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.UserInfoDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final UserRepository userRepository;

    @Autowired
    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login",
                                "api/shooter/all", "api/shooter/{id}", "api/shooter/add", "api/shooter/edit/{id}","api/shooter/delete/{id}",
                                "/api/news/all", "api/news/{id}", "api/news/add", "/api/news/edit/{id}", "/api/news/delete/{id}",
                                "/api/user/all", "/api/user/{id}", "/api/user/add", "/api/user/edit/{id}", "/api/user/delete/{userId}",
                                "api/competition/all", "api/competition/add", "api/competition/assign/{id}/{idd}"
                                ).permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoDetailsService(userRepository);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
