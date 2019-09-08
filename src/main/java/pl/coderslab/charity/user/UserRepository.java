package pl.coderslab.charity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.role.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    Optional <User> findByEmail(String email);

    List<User> findAllByRolesEquals(Role role);

    Optional <User> findByAuthenticationToken(String authenticationToken);
}
