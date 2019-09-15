package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.NotificationCreator;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.UserDto;
import pl.coderslab.charity.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;


@Controller
public class HomeController {

    private InstitutionService institutionService;
    private DonationService donationService;
    private UserService userService;
    private EmailService emailService;
    private NotificationCreator notificationCreator;

    public HomeController(InstitutionService institutionService, DonationService donationService, UserService userService, EmailService emailService, NotificationCreator notificationCreator) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.emailService = emailService;
        this.notificationCreator = notificationCreator;
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
    public String saveUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, HttpServletRequest request, Model model){
        if (bindingResult.hasErrors()){
            return "register";
        } else {
            UUID uuid = UUID.randomUUID();
            userDto.setAuthenticationToken(uuid.toString());
            userService.saveUser(userDto, "ROLE_UNAUTHORISED");
            emailService.sendRegisterConfirmation(
                    userDto.getEmail(),
                    userDto.getAuthenticationToken(),
                    request.getRequestURL().toString());
            String message = "Dziękujemy za zarejestrowanie konta.<br> " +
                    "Na maila przesłano link aktywacyjny.";
            return notificationCreator.showNotification(message, model);
        }
    }

    @GetMapping(path = "/confirm/{token}")
    public String confirmUser(@PathVariable("token") String authenticationToken, Model model){
        String message = "";
        if (userService.activateUser(authenticationToken)){
            message = "Konto zostało aktywowane!";
        } else {
            message = "Link wygasł!";
        }
        return notificationCreator.showNotification(message, model);
    }

    @GetMapping(path = "/remindPassword")
    public String remindPasswordForm(Model model){
        model.addAttribute("user", new UserDto());
        return "form-email-changing-password";
    }

    @PostMapping(path = "/remindPassword")
    public String sendChangingPasswordInstructionsOnEmail(@ModelAttribute("user") UserDto userDto, Model model, HttpServletRequest request){
        String message = "";
        if (userService.isUserExisting(userDto.getEmail())){
            emailService.sendChangingPasswordForm(
                    userDto.getEmail(),
                    userService.setUserAuthenticationToken(userDto.getEmail()),
                    request.getRequestURL().toString());
            message = "Wysłano na maila instrukcje do zmiany hasła!";
        } else {
            message = "Nie ma użytkownika o takim mailu!";
        }
        return notificationCreator.showNotification(message, model);
    }

    @GetMapping(path = "/changePassword/{token}")
    public String changePasswordForm(Model model, @PathVariable("token") String authenticationToken){
        if (userService.findUserByAuthenticationToken(authenticationToken).isPresent()){
            UserDto user = new UserDto();
            user.setAuthenticationToken(authenticationToken);
            model.addAttribute("user", user);
            return "form-change-password";
        } else {
            return notificationCreator.showNotification("Link wygasł!", model);
        }
    }

    @PostMapping(path = "changePassword")
    public String saveChangedPassword(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "form-change-password";
        } else {
            if (userDto.getPassword().equals(userDto.getSecondPassword())) {
                userService.updateUserPassword(userDto);
                return notificationCreator.showNotification("Zmieniono pomyślnie hasło!", model);
            } else {
                return notificationCreator.showNotification("Wpisane hasła muszą być te same!", model);
            }
        }
    }

}
