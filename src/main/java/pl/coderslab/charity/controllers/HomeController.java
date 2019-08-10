package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.UserDto;
import pl.coderslab.charity.user.UserService;


@Controller
public class HomeController {

    private InstitutionService institutionService;
    private DonationService donationService;
    private UserService userService;

    public HomeController(InstitutionService institutionService, DonationService donationService, UserService userService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionService.findAll());
        model.addAttribute("quantity", donationService.totalQuantity());
        model.addAttribute("donationsQuantity", donationService.countDonations());
        return "index";
    }

    @GetMapping(path = "/register")
    public String registerUser(Model model){
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping(path = "/register")
    public String saveUser(@ModelAttribute("user") UserDto userDto){
        userService.saveUser(userDto);
        //TODO Add register-confirmation site
        return "register-confirmation";
    }

}
