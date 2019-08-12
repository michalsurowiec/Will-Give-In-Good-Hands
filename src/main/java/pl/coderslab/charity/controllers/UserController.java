package pl.coderslab.charity.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.charity.CurrentUser;

@Controller
@RequestMapping(path = "/user")
@SessionAttributes("currentUser")
public class UserController {

    @RequestMapping(path = "/main")
    private String showUserPanel(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("currentUser", currentUser);
        return "user-main-page";
    }

}
