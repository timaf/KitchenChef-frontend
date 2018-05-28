package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/detail")
public class DetailController {

    private AddMealService addMealService;

    public DetailController(AddMealService addMealService) {
        this.addMealService = addMealService;
    }

    @GetMapping
    String page(){
        return "detail";
    }

    @GetMapping("/mealdetail/{id}")
    String detailPage(@PathVariable("id") String id, Model model){
        Meal meal = addMealService.detailPage(id);
        model.addAttribute("mealdetail", meal);
        return "detail";
    }
}
