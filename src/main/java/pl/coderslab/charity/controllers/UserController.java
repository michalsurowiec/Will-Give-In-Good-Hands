package pl.coderslab.charity.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String redirect = "access-denied";
        for (GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
                redirect = "admin-main-page";
            } else if (grantedAuthority.getAuthority().equals("ROLE_USER")){
                redirect = "user-main-page";
            }
        }
        model.addAttribute("currentUser", currentUser);
        return redirect;
    }

}
