package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.user.UserDto;
import pl.coderslab.charity.user.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/userCRUD")
public class UserManagementController {

    private UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/main")
    public String showAdmins(Model model){
        List<UserDto> users = userService.findAllUsersByRole("ROLE_USER");
        users.addAll(userService.findAllUsersByRole("ROLE_BANNED"));
        users.sort(Comparator.comparing(UserDto::getId));
        model.addAttribute("users", users);
        return "user-management";
    }

    @GetMapping(path = "/update/{id}")
    public String updateUser(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "user-update-form";
    }

    @PostMapping(path = "/update")
    public String saveUser(@ModelAttribute("user") @Valid UserDto user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "user-update-form";
        } else {
            userService.updateUser(user);
            if(!(user.getPassword().equals(""))){
                userService.updateUserPassword(user);
            }
            return "redirect:/admin/userCRUD/main";
        }
    }

    @GetMapping(path = "/block/{id}")
    public String blockUser(@PathVariable(name = "id") Long id){
        List<String> rolesNames = new ArrayList<>();
        rolesNames.add("ROLE_BANNED");
        userService.changeUserRole(id, rolesNames);
        return "redirect:/admin/userCRUD/main";
    }

    @GetMapping(path = "/unblock/{id}")
    public String unblockUser(@PathVariable(name = "id") Long id){
        List<String> rolesNames = new ArrayList<>();
        rolesNames.add("ROLE_USER");
        userService.changeUserRole(id, rolesNames);
        return "redirect:/admin/userCRUD/main";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/userCRUD/main";
    }
}
