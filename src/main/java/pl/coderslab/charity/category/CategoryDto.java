package pl.coderslab.charity.category;

import lombok.Data;
import pl.coderslab.charity.donation.DonationDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {

    private Long id;
    private String name;
    private List<DonationDto> donationsDto = new ArrayList<>();

}
