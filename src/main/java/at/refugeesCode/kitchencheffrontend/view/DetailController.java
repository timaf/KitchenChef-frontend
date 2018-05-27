package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
import at.refugeesCode.kitchencheffrontend.controller.DetailService;
import at.refugeesCode.kitchencheffrontend.model.Ingredient;
import at.refugeesCode.kitchencheffrontend.model.LocalIngredient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/meals")
public class DetailController {

    private DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    private
    @GetMapping
    String page(){
        return "detail";
    }

    @ModelAttribute("ingredients")
    List<LocalIngredient> showIngredients(String id){
    return detailService.getIngredients(id);
    }
}
