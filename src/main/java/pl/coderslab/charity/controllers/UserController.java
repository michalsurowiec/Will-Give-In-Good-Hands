package pl.coderslab.charity.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.CurrentUser;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.UserDto;
import pl.coderslab.charity.user.UserService;

@Controller
@RequestMapping(path = "/user")
@SessionAttributes("currentUser")
public class UserController {

    private UserService userService;
    private DonationService donationService;
    private CategoryService categoryService;
    private InstitutionService institutionService;

    public UserController(UserService userService, DonationService donationService, CategoryService categoryService, InstitutionService institutionService) {
        this.userService = userService;
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @RequestMapping(path = "/main")
    private String showUserPanel(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("donations", donationService.findDonationsByUserId(currentUser.getUser().getId()));
        model.addAttribute("institutions", institutionService.findAll());
        return "user-main-page";
    }

    @GetMapping(path = "/update")
    public String update(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("user", userService.findById(currentUser.getUser().getId()));
        return "user-self-update-form";
    }

    @PostMapping(path = "/update")
    public String save(@ModelAttribute("user") UserDto userDto, @AuthenticationPrincipal CurrentUser currentUser, Model model){
        userDto.setId(currentUser.getUser().getId());

        //After user updates his profile, this lines update information about user contained in Security
        currentUser.getUser().setName(userDto.getName());
        currentUser.getUser().setSurname(userDto.getSurname());
        currentUser.getUser().setEmail(userDto.getEmail());
        model.addAttribute("currentUser", currentUser);

        userService.updateUser(userDto);
        return "redirect:/user/main";
    }

    @GetMapping(path = "/updatePassword")
    public String updatePassword(Model model){
        model.addAttribute("user", new UserDto());
        return "user-self-update-password-form";
    }

    @PostMapping(path = "/updatePassword")
    public String savePassword(@ModelAttribute("user") UserDto userDto, @AuthenticationPrincipal CurrentUser currentUser){
        userDto.setId(currentUser.getUser().getId());
        userService.updateUserPassword(userDto);
        return "redirect:/user/main";
    }

}
