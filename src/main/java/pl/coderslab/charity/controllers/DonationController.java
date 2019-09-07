package pl.coderslab.charity.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.CurrentUser;
import pl.coderslab.charity.NotificationCreator;
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
    private NotificationCreator notificationCreator;

    public DonationController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService, NotificationCreator notificationCreator) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.notificationCreator = notificationCreator;
    }

    @GetMapping(path = "/form")
    private String showForm(Model model){
        model.addAttribute("donation", new DonationDto());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("institutions", institutionService.findAll());
        return "form";
    }

    @PostMapping(path = "/form")
    private String saveForm(@ModelAttribute("donation") DonationDto donationDto, @AuthenticationPrincipal CurrentUser currentUser, Model model){
        donationDto.setUser(currentUser.getUser().getId());
        donationDto.setCreationDate(LocalDate.now());
        donationService.saveDonation(donationDto);
        return notificationCreator.showNotification("Dziękujemy za przesłanie formularza.<br>" +
                "Na maila prześlemy wszelkie informacje o odbiorze.", model);
    }

    @GetMapping(path = "/details/{id}")
    private String donationDetails(@PathVariable("id") Long id, @AuthenticationPrincipal CurrentUser currentUser, Model model){
        String redirect = "";
        if (isDonationBelongingToUser(id, currentUser.getUser().getId())){
            model.addAttribute("donation", donationService.findDonationById(id));
            model.addAttribute("institutions", institutionService.findAll());
            redirect = "donation-details";
        } else {
            redirect = "access-denied";
        }
        return redirect;
    }

    @GetMapping(path = "/confirm/{id}")
    private String confirmDonationPickUp(@PathVariable("id") Long id, @AuthenticationPrincipal CurrentUser currentUser){
        String redirect = "";
        if (isDonationBelongingToUser(id, currentUser.getUser().getId())){
            donationService.changeDonationStatus(id, "Odebrane");
            donationService.setConfirmedPickUpDate(id);
            redirect = "redirect:/donation/details/" + id;
        } else {
            redirect = "access-denied";
        }
        return redirect;
    }

    private boolean isDonationBelongingToUser(Long donationId, Long userId){
        boolean result = false;
        for (DonationDto donationDtoEach : donationService.findDonationsByUserId(userId)){
            if (donationDtoEach.getId().equals(donationId)){
                result = true;
                break;
            }
        }
        return result;
    }

}
