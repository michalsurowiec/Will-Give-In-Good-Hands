package pl.coderslab.charity.institution;

import lombok.Data;
import pl.coderslab.charity.donation.DonationDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class InstitutionDto {

    private Long id;
    private String name;
    private String description;
    private List<Long> donationsId = new ArrayList<>();

    public InstitutionDto() {
    }

    public InstitutionDto(Institution institution) {
        this.id = institution.getId();
        this.name = institution.getName();
        this.description = institution.getDescription();
    }
}
