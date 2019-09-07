package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.NotificationCreator;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.UserDto;
import pl.coderslab.charity.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Controller
public class HomeController {

    private InstitutionService institutionService;
    private DonationService donationService;
    private UserService userService;
    private EmailService emailService;
//    private NotificationCreator notificationCreator;

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

    @GetMapping(path = "/remindPassword")
    public String remindPasswordForm(Model model){
        model.addAttribute("user", new UserDto());
        return "form-email-changing-password";
    }

    @PostMapping(path = "/remindPassword")
    public String sendChangingPasswordInstructionsOnEmail(@ModelAttribute("user") UserDto userDto, Model model){
        String message = "";
        if (userService.isUserExisting(userDto.getEmail())){
            emailService.sendChangingPasswordForm(userDto.getEmail());
//            message = "Wysłano na maila instrukcje do zmiany hasła!";
            model.addAttribute("notification", "Wysłano na maila instrukcje do zmiany hasła!");
        } else {
//            message = "Nie ma użytkownika o takim mailu!";
            model.addAttribute("notification", "Nie ma użytkownika o takim mailu!");
        }
//        return notificationCreator.showNotification(message, model);
        return "test";
    }

}
