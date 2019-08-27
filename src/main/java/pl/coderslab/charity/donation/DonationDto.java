package pl.coderslab.charity.donation;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryDto;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.institution.InstitutionDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class DonationDto {

    private Long id;
    private int quantity;
    private List<Long> categoriesId = new ArrayList<>();
    private List<String> categoriesNames = new ArrayList<>();
    private Long institution;
    private String street;
    private String city;
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;
    private Long user;
    private LocalDate creationDate;
    private LocalDate confirmedPickUpDate;
    private String status;

    public DonationDto() {
    }

    public DonationDto(Donation donation) {
        this.id = donation.getId();
        this.quantity = donation.getQuantity();
        for (Category category: donation.getCategories()){
            this.categoriesId.add(category.getId());
            this.categoriesNames.add(category.getName());
        }
        this.institution = donation.getInstitution().getId();
        this.street = donation.getStreet();
        this.city = donation.getCity();
        this.zipCode = donation.getZipCode();
        this.pickUpDate = donation.getPickUpDate();
        this.pickUpTime = donation.getPickUpTime();
        this.pickUpComment = donation.getPickUpComment();
        this.user = donation.getUser().getId();
        this.creationDate = donation.getCreationDate();
        this.status = donation.getStatus().getName();
        this.confirmedPickUpDate = donation.getConfirmedPickUpDate();
    }

}
