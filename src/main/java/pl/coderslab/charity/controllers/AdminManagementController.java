package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
