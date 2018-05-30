package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
import at.refugeesCode.kitchencheffrontend.controller.DetailService;
import at.refugeesCode.kitchencheffrontend.model.LocalIngredient;
import at.refugeesCode.kitchencheffrontend.model.Meal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/detail")
public class DetailController {

    private DetailService detailService;
    private AddMealService addMealService;

    public DetailController(DetailService detailService, AddMealService addMealService) {
        this.detailService = detailService;
        this.addMealService = addMealService;
    }

    @GetMapping
    String page() {
        return "detail";
    }

    @ModelAttribute("ingredients")
    List<LocalIngredient> showIngredients(String id) {
        return detailService.getIngredients(id);
    }

    @GetMapping("/mealdetail/{id}")
    String detailPage(@PathVariable("id") String id, Model model) {
        Meal meals = addMealService.detailPage(id);
        model.addAttribute("mealdetail", meals);
        return "detail";
    }
}
