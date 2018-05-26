package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
import at.refugeesCode.kitchencheffrontend.model.Meal;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.ui.Model;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.Random;

@Controller
@RequestMapping("/meal")
public class AddMealController {

    private AddMealService addMealService;

    private HttpServletRequest request;

    public AddMealController(AddMealService addMealService, HttpServletRequest request) {
        this.addMealService = addMealService;
        this.request = request;
    }

    @ModelAttribute("meal")
    Meal meal() {
        return new Meal();
    }

    @GetMapping("/meals")
    String getAllMeals(Model model) {
        Meal[] meal = addMealService.mealsList();
        model.addAttribute("meals", meal);
        return "meals";
    }

    @GetMapping
    String index() {
        return "index";
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

        // Generate a String Name for the Image name
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        if (!file.isEmpty()) {
            try {
                // Most change the Path according to your path at least just for now

                ServletContext context = request.getServletContext();
                String path = context.getRealPath("/");
                System.out.println(path);
                byte[] bytes = file.getBytes();
                File serverFile = new File(path + "../resources/static/images" + File.separator + generatedString + "-" +file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                String originalFilename = file.getOriginalFilename();
                String foodImage = generatedString + "-" + originalFilename;
                meal.setFoodImage(foodImage);

                redirectAttributes.addFlashAttribute("flash.message", "Successfully uploaded");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("flash.message", "Failed to upload");
                return "You failed to upload " + " => " + e.getMessage();
            }

        } else {
            return "You failed to upload " + " because the file was empty.";
        }
        addMealService.createMeal(meal);
        return "redirect:/";
    }

    @GetMapping("/mealdetail/{id}")
    String detailPage(@PathVariable("id") String id,Model model){
        Meal meals = addMealService.detailPage(id);
        model.addAttribute("mealdetail", meals);
        return "detail";
    }

}
