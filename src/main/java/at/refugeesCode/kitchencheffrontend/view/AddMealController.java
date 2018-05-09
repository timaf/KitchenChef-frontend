package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.cotroller.AddMealService;
import at.refugeesCode.kitchencheffrontend.model.Meal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addMeal")
public class AddMealController {

    private AddMealService addMealService;

    public AddMealController(AddMealService addMealService) {
        this.addMealService = addMealService;
    }

    @ModelAttribute("meal")
    Meal meal(){
        return new Meal();
    }

    @GetMapping
    String page(){
        return "addMeal";
    }

    @PostMapping
    String post(Meal meal){
    addMealService.send(meal);
    return "redirect:/";
    }
}
