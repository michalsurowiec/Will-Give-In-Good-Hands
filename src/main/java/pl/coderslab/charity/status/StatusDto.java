package pl.coderslab.charity.status;

import lombok.Data;
import pl.coderslab.charity.donation.Donation;

import java.util.ArrayList;
import java.util.List;

@Data
public class StatusDto {

    private Long id;
    private String name;
    private List<Long> donationsId = new ArrayList<>();

    public StatusDto(Status status){
        this.id = status.getId();
        this.name = status.getName();
        for (Donation donation : status.getDonations()){
            this.donationsId.add(donation.getId());
        }
    }

    public StatusDto() {
    }
}
