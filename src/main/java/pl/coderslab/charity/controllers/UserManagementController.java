package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return "admin-management";
    }

}
