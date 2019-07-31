package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.donation.DonationDto;
import pl.coderslab.charity.institution.InstitutionService;

@Controller
public class DonationController {

    private CategoryService categoryService;
    private InstitutionService institutionService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @GetMapping("/form")
    private String showForm(Model model){
        model.addAttribute("donation", new DonationDto());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("institutions", institutionService.findAll());
        return "form";
    }

}
