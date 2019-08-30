package pl.coderslab.charity.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        donationDto.setCreationDate(LocalDate.now());
        donationService.saveDonation(donationDto);
        return "form-confirmation";
    }

    @GetMapping(path = "/details/{id}")
    private String donationDetails(@PathVariable("id") Long id, @AuthenticationPrincipal CurrentUser currentUser, Model model){
        String redirect = "access-denied";
        for (DonationDto donationDtoEach : donationService.findDonationsByUserId(currentUser.getUser().getId())){
            if (donationDtoEach.getId().equals(id)){
                model.addAttribute("donation", donationDtoEach);
                model.addAttribute("institutions", institutionService.findAll());
                redirect = "donation-details";
            }
        }
        return redirect;
    }

}
