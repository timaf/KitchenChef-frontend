package at.refugeesCode.kitchencheffrontend.view;


import at.refugeesCode.kitchencheffrontend.persistence.model.AppUser;
import at.refugeesCode.kitchencheffrontend.persistence.model.Ingredient;
import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import at.refugeesCode.kitchencheffrontend.persistence.repository.MealRepository;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class AddMealController {

    private MealRepository mealRepository;
    private UserRepository userRepository;
    private HttpServletRequest request;

    public AddMealController(MealRepository mealRepository, UserRepository userRepository, HttpServletRequest request) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
        this.request = request;
    }

    @ModelAttribute("meal")
    Meal meal() {
        return new Meal();
    }

    @GetMapping(value="/addmeal")
    String createAMeal() {
        return "addmeal";
    }

    @ModelAttribute("newUser")
    AppUser newUser() {
        return new AppUser();
    }

   /* @ModelAttribute("ingredient")
    Ingredient newIngredient() {
        return new Ingredient();
    }*/

    @ModelAttribute("unitList")
    List<String> symptomsList(){
        return Stream.of("Liter" ,"Kg" ,"g" , "Piece").collect(Collectors.toList());
    }

    @RequestMapping(value="/addmeal", params={"addIngredient"})
    public String addIngredient( final Meal meal,final BindingResult bindingResult) {
        meal.getIngredients().add(new Ingredient());
        return "addmeal";
    }

    @RequestMapping(value="/addmeal", params={"removeIngredient"})
    public String removeIngredient(final Meal meal,final BindingResult bindingResult,final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeIngredient"));
        meal.getIngredients().remove(rowId.intValue());
        return "addmeal";
    }

    @PostMapping(value="/addmeal")
    String createNewMeal(Meal meal, @RequestParam("file") MultipartFile file,
                         RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
        {
            System.out.println("Error");
        }
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
                File serverFile = new File(path + "../resources/static/images" + File.separator + generatedString + "-" + file.getOriginalFilename());
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

        mealRepository.save(meal);
        return "redirect:/addmeal";
    }



}