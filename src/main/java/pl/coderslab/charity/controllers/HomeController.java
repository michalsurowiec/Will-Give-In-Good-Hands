package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.UserDto;
import pl.coderslab.charity.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.util.UUID;


@Controller
public class HomeController {

    private InstitutionService institutionService;
    private DonationService donationService;
    private UserService userService;
    private EmailService emailService;

    public HomeController(InstitutionService institutionService, DonationService donationService, UserService userService, EmailService emailService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.emailService = emailService;
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
    public String saveUser(@ModelAttribute("user") UserDto userDto, HttpServletRequest request){
        UUID uuid = UUID.randomUUID();
        userDto.setAuthenticationToken(uuid.toString());
        userService.saveUser(userDto, "ROLE_UNAUTHORISED");
        emailService.sendRegisterConfirmation(userDto.getEmail(), userDto.getAuthenticationToken(), request.getRequestURL().toString());
        return "register-confirmation";
    }

    @GetMapping(path = "/confirm/{token}")
    public String confirmUser(@PathVariable("token") String authenticationToken){
        String redirect = "";
        if (userService.activateUser(authenticationToken)){
            redirect = "activation-successful";
        } else {
            redirect = "activation-unsuccessful";
        }
        return redirect;
    }

}
