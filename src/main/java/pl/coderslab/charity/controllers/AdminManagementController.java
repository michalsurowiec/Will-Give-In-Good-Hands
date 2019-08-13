package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.user.UserDto;
import pl.coderslab.charity.user.UserService;

@Controller
@RequestMapping(path = "/admin/adminCRUD")
public class AdminManagementController {

    private UserService userService;

    public AdminManagementController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/main")
    public String showAdmins(Model model){
        model.addAttribute("admins", userService.findAllAdmins());
        return "admin-management";
    }

    @GetMapping(path = "/create")
    public String addAdmin(Model model){
        model.addAttribute("admin", new UserDto());
        return "admin-create-form";
    }

    @PostMapping(path = "/create")
    public String saveAdmin(@ModelAttribute("admin") UserDto admin){
        userService.saveUser(admin, "ROLE_ADMIN");
        return "redirect:/admin/adminCRUD/main";
    }

}
