package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("admins", userService.findAllUsersByRole("ROLE_ADMIN"));
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

    @GetMapping(path = "/update/{id}")
    public String updateAdmin(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("admin", userService.findById(id));
        return "admin-update-form";
    }

    @PostMapping(path = "/update")
    public String saveUpdatedAdmin(@ModelAttribute("admin") UserDto admin){
        userService.updateUser(admin);
        if(!(admin.getPassword().equals(""))){
            userService.updateUserPassword(admin);
        }
        return "redirect:/admin/adminCRUD/main";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteAdmin(@PathVariable(name = "id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/adminCRUD/main";
    }

}
