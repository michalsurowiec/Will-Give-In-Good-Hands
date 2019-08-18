package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.institution.InstitutionService;

@Controller
@RequestMapping(path = "/admin/institutionCRUD")
public class InstitutionManagementController {

    private InstitutionService institutionService;

    public InstitutionManagementController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @RequestMapping(path = "/main")
    public String showAdmins(Model model){
        model.addAttribute("institutions", institutionService.findAll());
        return "institution-management";
    }

}
