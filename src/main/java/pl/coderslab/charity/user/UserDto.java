package pl.coderslab.charity.user;

import lombok.Data;
import pl.coderslab.charity.role.Role;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private List<Long> rolesId = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        for (Role role : user.getRoles()){
            this.rolesId.add(role.getId());
        }
    }
}
