package pl.coderslab.charity.status;

import lombok.Data;
import pl.coderslab.charity.donation.Donation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "status")
    private List<Donation> donations = new ArrayList<>();
}
