package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.cotroller.AddMealService;
import at.refugeesCode.kitchencheffrontend.model.Meal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

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

    @ModelAttribute("meal")
    Meal meal() {
        return new Meal();
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
        model.addAttribute("meal", meal);
        return "createmeal";
    }
    @PostMapping("meal")
    String createNewMeal(Meal meal, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
                        @RequestParam("cookName") String cookName, @RequestParam("mealName") String mealName, @RequestParam("mealDescription") String mealDescription,
                        @RequestParam("ingredients") String ingredients, @RequestParam("numberOfPeople") int numberOfPeople, @RequestParam("startCookingTime")LocalTime startCookingTime,
                        @RequestParam("preparationTime") Long preparationTime, @RequestParam("year") int year, @RequestParam("month") int month, @RequestParam("day") int day) {
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

        if (!file.isEmpty()) {
            try {
                //"C:/Users/Wael/IdeaProjects/Kitchen cheff/Kitchen-app-frontend/src/main/resources/static/images"
                String UPLOADED_FOLDER = "/Users/Wael/Desktop/KitchenChef-frontend/src/main/resources/static/images";
                byte[] bytes = file.getBytes();
                File serverFile = new File(UPLOADED_FOLDER + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                meal.setFoodImage(file.getOriginalFilename());
                //save in database
                //this.meal = meal;
                // profilesRepository.save(this.meal);
                redirectAttributes.addFlashAttribute("flash.message", "Successfully uploaded");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("flash.message", "Failed to upload");
                return "You failed to upload " + " => " + e.getMessage();
            }

        } else {
            return "You failed to upload " + " because the file was empty.";
        }
        System.out.println(meal.toString());
        addMealService.createMeal(meal);
        return "redirect:/";
    }

}
