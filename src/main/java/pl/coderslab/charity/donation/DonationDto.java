package pl.coderslab.charity.donation;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.category.CategoryDto;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.category.CategoryService;
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
    private List<Long> categoriesId = new ArrayList<>();
    private Long institution;
    private String street;
    private String city;
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;

}
