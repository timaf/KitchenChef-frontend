package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.cotroller.AddMealService;
import at.refugeesCode.kitchencheffrontend.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping()
public class AddMealController {

    private AddMealService addMealService;
    private RestTemplate restTemplate;

    public AddMealController(AddMealService addMealService, RestTemplate restTemplate) {
        this.addMealService = addMealService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    String getAllMeals(Model model) {
        Meal[] meal = addMealService.mealsList();
        model.addAttribute("meals", meal);
        return "meals";
    }

    @GetMapping("/create-meal")
    String createAMeal(Model model) {
        Meal[] meal = addMealService.mealsList();
        model.addAttribute("meals", meal);
        return "createmeal";
    }
    @PostMapping("meals")
    String createNewMeal(@RequestParam("cookName") String cookName, @RequestParam("mealName") String mealName, @RequestParam("mealDescription") String mealDescription,
                        @RequestParam("ingredients") String ingredients, @RequestParam("numberOfPeople") int numberOfPeople, @RequestParam("startCookingTime")LocalTime startCookingTime,
                        @RequestParam("preparationTime") Long preparationTime, @RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("day") int day) {
        Meal meal = new Meal();
        meal.setCookName(cookName);
        meal.setMealName(mealName);
        meal.setMealDescription(mealDescription);
        meal.setIngredients(ingredients);
        meal.setNumberOfPeople(numberOfPeople);
        meal.setStartCookingTime(startCookingTime);
        meal.setPreparationTime(preparationTime);
        meal.setYear(year);
        meal.setMonth(month);
        meal.setDay(day);
        addMealService.createMeal(meal);
        return "redirect:/";
    }

}
