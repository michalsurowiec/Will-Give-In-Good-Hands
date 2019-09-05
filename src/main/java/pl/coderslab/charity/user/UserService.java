package pl.coderslab.charity.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.role.Role;
import pl.coderslab.charity.role.RoleRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
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
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByName(role))));
        user.setAuthenticationToken(userDto.getAuthenticationToken());
        userRepository.save(user);
    }

    public void updateUser(UserDto userDto){
        User user = userRepository.findById(userDto.getId()).get();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        userRepository.save(user);
    }

    public void updateUserPassword(UserDto userDto){
        User user = userRepository.findById(userDto.getId()).get();
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    public List<UserDto> findAllUsersByRole(String role){
        return userRepository.findAllByRolesEquals(roleRepository.findByName(role)).stream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto findById(Long id){
        return new UserDto(userRepository.findById(id).get());
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void changeUserRole(Long id, List<String> rolesNames){
        User user = userRepository.findById(id).get();
        Set<Role> roles = new HashSet<>();
        for (String roleName : rolesNames){
            roles.add(roleRepository.findByName(roleName));
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    public boolean activateUser(String authenticationToken){
        boolean result = false;
        List<User> usersUnauthorised = userRepository.findAllByRolesEquals(roleRepository.findByName("ROLE_UNAUTHORISED"));
        for (User userEach : usersUnauthorised){
            if (userEach.getAuthenticationToken().equals(authenticationToken)){
                List<String> rolesNames = new ArrayList<>();
                rolesNames.add("ROLE_USER");
                changeUserRole(userEach.getId(), rolesNames);
                result = true;
            }
        }
        return result;
    }

}
