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
    private List<DonationDto> donationsDto = new ArrayList<>();
}
