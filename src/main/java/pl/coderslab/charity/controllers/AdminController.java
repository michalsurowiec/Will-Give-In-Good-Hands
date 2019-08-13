package pl.coderslab.charity.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.charity.CurrentUser;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    @RequestMapping(path = "/main")
    private String showUserPanel(){
        return "admin-main-page";
    }

}
