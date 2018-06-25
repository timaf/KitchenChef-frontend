package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.persistence.model.*;
import at.refugeesCode.kitchencheffrontend.persistence.repository.MealRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/mealDetail")
public class DetailController {

    private Boolean disable;
    private MealRepository mealRepository;
    private String mealId;
    private Boolean joined;
    private String volunteerName;

    public DetailController(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @ModelAttribute("newUser")
    AppUser newUser() {
        return new AppUser();
    }


   /* @GetMapping("/shoppinglist/{id}")
    String showShoppingList(@PathVariable("id") String id, Model model) {
        Meal meal = addMealService.detailPage(id);
        model.addAttribute("shoppinglist", meal.getIngredients());
        return "shoppinglist";
    }*/


   @GetMapping("/{id}")
    public String detailPage(@PathVariable("id") String id, Model model, Principal principal){
        mealId = id;
        disable = principal != null ? false : true;
        mealRepository.findById(id).ifPresent(meal -> {
            if (principal != null) {
                volunteerName = principal.getName();
                joined = meal.getAttendees().stream().anyMatch(e -> e.equals(volunteerName));
            }
            model.addAttribute("mealdetail", meal);
            model.addAttribute("ingredients", meal.getIngredients());
            model.addAttribute("disable", disable);
            model.addAttribute("joinedEating", joined);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            model.addAttribute("username", username);
        });
        return "detail";
    }


    @PostMapping(value = "/{id}/signUp", params = "signup=eat")
     public  String saveAttendance(Model model) {
        mealRepository.findById(mealId).ifPresent(meal -> {
            joined = meal.getAttendees().stream().anyMatch(e -> e.equals(volunteerName));
            if (joined) {
                meal.getAttendees().remove(volunteerName);
                joined = false;
            } else {
                meal.getAttendees().add(volunteerName);
                joined = true;
            }
            Meal updatedMeal = save(meal);
            model.addAttribute("joinedEating", joined);
            model.addAttribute("mealdetail", updatedMeal);
        });
        return "detail";
    }

    @PostMapping(value = "/{id}/signUp", params = "signup=cleaner")
    String saveCleaner(Model model) {
        mealRepository.findById(mealId).ifPresent(meal -> {
            if (meal.getCleaner() == null) {
                meal.setCleaner(volunteerName);
                meal.getAttendees().add(volunteerName);
                joined = true;
            } else if (meal.getCleaner().equals(volunteerName)) {
                meal.setCleaner(null);
                meal.getAttendees().remove(volunteerName);
                joined = false;
            } else {
            }
            Meal updatedMeal = save(meal);
            model.addAttribute("joinedEating", joined);
            model.addAttribute("mealdetail", updatedMeal);

        });
        return "detail";
    }

    @PostMapping(value = "/{id}/signUp", params = "signup=helper")
    String saveHelper(Model model) {
        mealRepository.findById(mealId).ifPresent(meal -> {
            if (meal.getHelper() == null) {
                meal.setHelper(volunteerName);
                meal.getAttendees().add(volunteerName);
                joined = true;
            } else if (meal.getHelper().equals(volunteerName)) {
                meal.setHelper(null);
                meal.getAttendees().remove(volunteerName);
                joined = false;
            } else {
            }
            Meal updatedMeal = save(meal);
            model.addAttribute("joinedEating", joined);
            model.addAttribute("mealdetail", updatedMeal);

        });
        return "detail";
    }

    @PostMapping(value = "/{id}/signUp", params = "signup=shopper")
    String saveShopper(Principal principal, Model model) {
        mealRepository.findById(mealId).ifPresent(meal -> {
            if (meal.getShopper() == null) {
                meal.setShopper(volunteerName);
                meal.getAttendees().add(volunteerName);
                joined = true;
            } else if (meal.getShopper().equals(volunteerName)) {
                meal.setShopper(null);
                meal.getAttendees().remove(volunteerName);
                joined = false;
            } else {
            }
            Meal updatedMeal = save(meal);
            model.addAttribute("joinedEating", joined);
            model.addAttribute("mealdetail", updatedMeal);

        });
        return "detail";
    }

    @Transactional
    public Meal save(Meal meal) {
        return mealRepository.save(meal);
    }
}

