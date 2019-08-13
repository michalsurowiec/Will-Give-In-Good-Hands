package pl.coderslab.charity.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.role.Role;
import pl.coderslab.charity.role.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public void saveUser (UserDto userDto, String role){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByName(role))));
        userRepository.save(user);
    }

    public List<UserDto> findAllAdmins(){
        return userRepository.findAllByRolesEquals(roleRepository.findByName("ROLE_ADMIN")).stream().map(UserDto::new).collect(Collectors.toList());
    }
}
