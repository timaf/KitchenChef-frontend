package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
<<<<<<< HEAD
import at.refugeesCode.kitchencheffrontend.controller.DetailService;
import at.refugeesCode.kitchencheffrontend.persistence.model.LocalIngredient;
import at.refugeesCode.kitchencheffrontend.model.Meal;
=======
import at.refugeesCode.kitchencheffrontend.persistence.model.AppUser;
import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
>>>>>>> upstream/master
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

<<<<<<< HEAD

=======
>>>>>>> upstream/master
@Controller
@RequestMapping("/detail")
public class DetailController {

<<<<<<< HEAD
    private DetailService detailService;
    private AddMealService addMealService;

    public DetailController(DetailService detailService, AddMealService addMealService) {
        this.detailService = detailService;
        this.addMealService = addMealService;
=======
    private AddMealService addMealService;
    private UserRepository userRepository;

    public DetailController(AddMealService addMealService, UserRepository userRepository) {
        this.addMealService = addMealService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("users")
    List<AppUser> users() {
        return userRepository.findAll();
    }

    @ModelAttribute("newUser")
    AppUser newUser() {
        return new AppUser();
>>>>>>> upstream/master
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

    @GetMapping("/mealdetail/{id}")
    String detailPage(@PathVariable("id") String id, Model model){
        Meal meal = addMealService.detailPage(id);
        model.addAttribute("mealdetail", meal);
        return "detail";
    }
}
