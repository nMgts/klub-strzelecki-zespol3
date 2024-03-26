package pl.klubstrzelecki.serwer_klub_strzelecki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
