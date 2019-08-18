package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.institution.InstitutionDto;
import pl.coderslab.charity.institution.InstitutionService;

@Controller
@RequestMapping(path = "/admin/institutionCRUD")
public class InstitutionManagementController {

    private InstitutionService institutionService;

    public InstitutionManagementController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @RequestMapping(path = "/main")
    public String showInstitutions(Model model){
        model.addAttribute("institutions", institutionService.findAll());
        return "institution-management";
    }

    @GetMapping(path = "/create")
    public String addInstitution(Model model){
        model.addAttribute("institution", new InstitutionDto());
        return "institution-create-form";
    }

    @PostMapping(path = "/create")
    public String saveInstitution(@ModelAttribute("admin") InstitutionDto institutionDto){
        institutionService.create(institutionDto);
        return "redirect:/admin/institutionCRUD/main";
    }

}
