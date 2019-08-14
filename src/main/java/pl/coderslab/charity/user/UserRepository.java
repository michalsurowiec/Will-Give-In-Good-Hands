package pl.coderslab.charity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.role.Role;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findByEmail(String email);

    List<User> findAllByRolesEquals(Role role);
}
