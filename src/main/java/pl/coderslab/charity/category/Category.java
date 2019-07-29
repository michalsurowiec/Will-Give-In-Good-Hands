package pl.coderslab.charity.category;

import lombok.Data;
import pl.coderslab.charity.donation.Donation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany (mappedBy = "categories")
    private List<Donation> donations = new ArrayList<>();

}
