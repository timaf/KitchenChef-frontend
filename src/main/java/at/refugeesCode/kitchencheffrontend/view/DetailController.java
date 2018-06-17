package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
import at.refugeesCode.kitchencheffrontend.controller.DetailService;
import at.refugeesCode.kitchencheffrontend.persistence.model.*;
import at.refugeesCode.kitchencheffrontend.persistence.repository.MealRepository;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/detail")
public class DetailController {

    private Boolean disable;
    private AddMealService addMealService;
    private UserRepository userRepository;
    private MealRepository mealRepository;
    private DetailService detailService;
    private String mealId;
    private Boolean joined;
    private String volunteerName;

    public DetailController(AddMealService addMealService, UserRepository userRepository, MealRepository mealRepository, DetailService detailService) {
        this.addMealService = addMealService;
        this.userRepository = userRepository;
        this.mealRepository = mealRepository;
        this.detailService = detailService;
    }

    @ModelAttribute("newUser")
    AppUser newUser() {
        return new AppUser();
    }

    @GetMapping
    String page() {
        return "detail";
    }

   /* @GetMapping("/mealdetail/shoppinglist/{id}")
    String showShoppingList(@PathVariable("id") String id, Model model) {
        Meal meal = addMealService.detailPage(id);
        model.addAttribute("shoppinglist", meal.getIngredients());
        return "shoppinglist";
    }*/

    @GetMapping("/mealdetail/{id}")
    String detailPage(@PathVariable("id") String id, Model model, Principal principal) {
        mealId = id;
        disable = principal != null ? false : true;
        mealRepository.findById(id).ifPresent(meal -> {
            List <Ingredient> ingredients = meal.getIngredients();
            if (principal != null) {
                volunteerName = principal.getName();
                joined = meal.getAttendees().stream().anyMatch(e -> e.equals(volunteerName));
            }
            model.addAttribute("mealdetail", meal);
            model.addAttribute("ingredients", ingredients);
            model.addAttribute("disable", disable);
            model.addAttribute("joinedEating", joined);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            model.addAttribute("username", username);
        });
        return "detail";
    }

    @PostMapping(value = "/mealdetail/{id}/signUp", params = "signup=eat")
    String saveAttendance(Principal principal, Model model) {
        mealRepository.findById(mealId).ifPresent(meal -> {
            joined = meal.getAttendees().stream().anyMatch(e -> e.equals(volunteerName));
            if (joined) {
                meal.getAttendees().remove(volunteerName);
                joined = false;
            } else {
                meal.getAttendees().add(volunteerName);
                joined = true;
            }
            model.addAttribute("joinedEating", joined);
            model.addAttribute("mealdetail", meal);
            Meal updatedMeal = save(meal);
        });
        return "detail";
    }

    @PostMapping(value = "/mealdetail/{id}/signUp", params = "signup=cleaner")
    String saveCleaner(Principal principal, Model model) {
        mealRepository.findById(mealId).ifPresent(meal -> {
            if (meal.getCleaner() == null) {
                meal.setCleaner(volunteerName);
                meal.getAttendees().add(volunteerName);
            } else if (meal.getCleaner().equals(volunteerName)) {
                meal.setCleaner(null);
                meal.getAttendees().remove(volunteerName);
            } else {
            }
            model.addAttribute("mealdetail", meal);
            Meal updatedMeal = save(meal);
        });
        return "detail";
    }

    @PostMapping(value = "/mealdetail/{id}/signUp", params = "signup=helper")
    String saveHelper(Principal principal, Model model) {
        mealRepository.findById(mealId).ifPresent(meal -> {
            if (meal.getHelper() == null) {
                meal.setHelper(volunteerName);
                meal.getAttendees().add(volunteerName);
            } else if (meal.getHelper().equals(volunteerName)) {
                meal.setHelper(null);
                meal.getAttendees().remove(volunteerName);
            } else {
            }
            model.addAttribute("mealdetail", meal);
            Meal updatedMeal = save(meal);
        });
        return "detail";
    }

    @PostMapping(value = "/mealdetail/{id}/signUp", params = "signup=shopper")
    String saveShoper(Principal principal, Model model) {
        mealRepository.findById(mealId).ifPresent(meal -> {
            if (meal.getShopper() == null) {
                meal.setShopper(volunteerName);
                meal.getAttendees().add(volunteerName);
            } else if (meal.getShopper().equals(volunteerName)) {
                meal.setShopper(null);
                meal.getAttendees().remove(volunteerName);
            } else {
            }

            model.addAttribute("mealdetail", meal);
            Meal updatedMeal = save(meal);
        });
        return "detail";
    }

    @Transactional
    public Meal save(Meal meal) {
        return mealRepository.save(meal);
    }
}

