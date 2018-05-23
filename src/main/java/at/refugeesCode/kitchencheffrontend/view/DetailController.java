package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
import at.refugeesCode.kitchencheffrontend.controller.DetailService;
import at.refugeesCode.kitchencheffrontend.persistence.model.AppUser;
import at.refugeesCode.kitchencheffrontend.persistence.model.Ingredient;
import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/detail")
public class DetailController {

    private Boolean disable;
    private AddMealService addMealService;
    private UserRepository userRepository;
    private DetailService detailService;

    public DetailController(AddMealService addMealService, UserRepository userRepository, DetailService detailService) {
        this.addMealService = addMealService;
        this.userRepository = userRepository;
        this.detailService = detailService;
    }

  /*  @ModelAttribute("users")
    List<AppUser> users() {
        return userRepository.findAll();
    }*/

    @ModelAttribute("newUser")
    AppUser newUser() {
        return new AppUser();
    }
    @GetMapping
    String page() {
        return "detail";
    }

    @GetMapping("/mealdetail/shoppinglist/{id}")
    String showShoppingList(@PathVariable("id") String id, Model model) {
        Meal meal = addMealService.detailPage(id);
        model.addAttribute("shoppinglist", meal.getIngredients());
        return "shoppinglist";
    }

    @GetMapping("/mealdetail/{id}")
    String detailPage(@PathVariable("id") String id, Model model, Principal principal){
        Meal meal = addMealService.detailPage(id);
        disable = principal != null ? false : true;
        List<Ingredient> ingredients = meal.getIngredients();
        model.addAttribute("mealdetail", meal);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("disable", disable);
        return "detail";
    }
}
