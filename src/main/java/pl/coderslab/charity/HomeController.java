package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.institution.InstitutionService;


@Controller
public class HomeController {

    private InstitutionService institutionService;

    public HomeController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionService.findAll());
        model.addAttribute("quantity", institutionService.totalQuantity());
        return "index";
    }
}
