package pl.coderslab.charity.user;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private List<Long> rolesId;

}
