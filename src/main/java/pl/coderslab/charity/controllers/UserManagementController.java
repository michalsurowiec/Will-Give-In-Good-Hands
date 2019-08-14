package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.user.UserDto;
import pl.coderslab.charity.user.UserService;

@Controller
@RequestMapping(path = "/admin/userCRUD")
public class UserManagementController {

    private UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    //Zmienić metodę findAllAdmins w sposób pozwalający na szukanie użytkowników po ich rolach a także stworzyć JSP
    @RequestMapping(path = "/main")
    public String showAdmins(Model model){
        model.addAttribute("users", userService.findAllUsersByRole("ROLE_USER"));
        return "user-management";
    }

    @GetMapping(path = "/update/{id}")
    public String updateUser(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "user-update-form";
    }

    @PostMapping(path = "/save")
    public String saveAdmin(@ModelAttribute("user") UserDto user){
        userService.saveUser(user, "ROLE_USER");
        return "redirect:/admin/userCRUD/main";
    }

    @GetMapping(path = "/block/{id}")
    public String blockUser(@PathVariable(name = "id") Long id){
        userService.blockUser(id);
        return "redirect:/admin/userCRUD/main";
    }
}
