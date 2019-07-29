package pl.coderslab.charity.donation;

import lombok.Data;
import pl.coderslab.charity.category.CategoryDto;
import pl.coderslab.charity.institution.InstitutionDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class DonationDto {

    private Long id;
    private int quantity;
    private List<CategoryDto> categoriesDto = new ArrayList<>();
    private InstitutionDto institutionDto;
    private String street;
    private String city;
    private String zipCode;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;

}
