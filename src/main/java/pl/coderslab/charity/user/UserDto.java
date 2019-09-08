package pl.coderslab.charity.user;

import lombok.Data;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.role.Role;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String secondPassword;
    private String name;
    private String surname;
    private List<Long> rolesId = new ArrayList<>();
    private List<String> rolesName = new ArrayList<>();
//    private List<Long> donationsId = new ArrayList<>();
    private String authenticationToken;

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
            this.rolesName.add(role.getName());
        }
//        for (Donation donation : user.getDonations()){
//            this.donationsId.add(donation.getId());
//        }
        this.authenticationToken = user.getAuthenticationToken();
    }

    public boolean comparePasswords(){
        return password.equals(secondPassword);
    }
}
