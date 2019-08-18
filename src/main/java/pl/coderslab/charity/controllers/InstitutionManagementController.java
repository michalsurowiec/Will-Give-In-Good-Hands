package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String saveInstitution(@ModelAttribute("institution") InstitutionDto institutionDto){
        institutionService.create(institutionDto);
        return "redirect:/admin/institutionCRUD/main";
    }

    @GetMapping(path = "/update/{id}")
    public String updateInstitution(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("institution", institutionService.findById(id));
        return "institution-update-form";
    }

    @PostMapping(path = "/update")
    public String saveUpdatedInstitution(@ModelAttribute("institution") InstitutionDto institutionDto){
        institutionService.update(institutionDto);
        return "redirect:/admin/institutionCRUD/main";
    }

}
