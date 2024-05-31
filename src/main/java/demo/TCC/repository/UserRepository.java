package demo.TCC.repository;

import demo.TCC.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

}
