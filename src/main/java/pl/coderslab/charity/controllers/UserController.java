package pl.coderslab.charity.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.CurrentUser;
import pl.coderslab.charity.user.UserDto;
import pl.coderslab.charity.user.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/update")
    public String update(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("user", userService.findById(currentUser.getUser().getId()));
        return "user-self-update-form";
    }

    @PostMapping(path = "/update")
    public String save(@ModelAttribute("user") UserDto userDto, @AuthenticationPrincipal CurrentUser currentUser){
        userDto.setId(currentUser.getUser().getId());
        userService.updateUser(userDto);
        return "redirect:/user/main";
    }

//    @GetMapping(path = "/updatePassword")
//    public String updatePassword(Model model, @AuthenticationPrincipal CurrentUser currentUser){
//        model.addAttribute("user", userService.findById(currentUser.getUser().getId()));
//        return "user-self-update-password-form";
//    }
//
//    @PostMapping(path = "/updatePassword")
//    public String savePassword(@ModelAttribute("user") UserDto userDto){
//        userService.updateUser(userDto);
//        return "redirect:/user/main";
//    }

}
