package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.donation.DonationDto;

@Controller
public class DonationController {

    private CategoryService categoryService;

    public DonationController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/form")
    private String showForm(Model model){
        model.addAttribute("donation", new DonationDto());
        model.addAttribute("categories", categoryService.findAll());
        return "form";
    }

}
