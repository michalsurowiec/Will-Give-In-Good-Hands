package pl.coderslab.charity.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.CurrentUser;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.donation.DonationDto;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.InstitutionService;

import java.time.LocalDate;

@Controller
@RequestMapping(path = "/donation")
public class DonationController {

    private CategoryService categoryService;
    private InstitutionService institutionService;
    private DonationService donationService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping(path = "/form")
    private String showForm(Model model){
        model.addAttribute("donation", new DonationDto());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("institutions", institutionService.findAll());
        return "form";
    }

    @PostMapping(path = "/form")
    private String saveForm(@ModelAttribute("donation") DonationDto donationDto, @AuthenticationPrincipal CurrentUser currentUser){
        donationDto.setUser(currentUser.getUser().getId());
        donationDto.setStatus("Nieodebrane");
        donationDto.setCreationDate(LocalDate.now());
        donationService.saveDonation(donationDto);
        return "form-confirmation";
    }

}
