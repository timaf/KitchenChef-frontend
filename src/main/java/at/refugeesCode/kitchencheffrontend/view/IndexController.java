package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
import at.refugeesCode.kitchencheffrontend.model.AppUser;
import at.refugeesCode.kitchencheffrontend.model.Meal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    private AddMealService addMealService;

    public IndexController(AddMealService addMealService) {
        this.addMealService = addMealService;
    }

    @GetMapping
    String page(Model model){
        Meal[] meal = addMealService.mealsList();
        model.addAttribute("meals", meal);
        return "index";
    }
    @ModelAttribute("appUser")
    AppUser newUser(){
        return new AppUser();
    }


    @PostMapping
    String post(AppUser appUser){
        return "redirect:/";
    }

}

