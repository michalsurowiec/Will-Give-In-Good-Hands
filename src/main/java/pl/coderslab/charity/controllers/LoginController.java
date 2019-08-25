package pl.coderslab.charity.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.charity.CurrentUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("currentUser")
public class LoginController
{
    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(path = "/redirect")
    private String redirect(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        String redirect = "access-denied";
        for (GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
                redirect = "admin-main-page";
            } else if (grantedAuthority.getAuthority().equals("ROLE_USER")){
                redirect = "redirect:/user/main";
            }
        }
        model.addAttribute("currentUser", currentUser);
        return redirect;
    }

    @GetMapping(path = "/deniedAccess")
    public String deniedAcces(){
        return "access-denied";
    }
}