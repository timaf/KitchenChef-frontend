package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
import at.refugeesCode.kitchencheffrontend.persistence.model.AppUser;
import at.refugeesCode.kitchencheffrontend.persistence.model.Ingredient;
import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

@Controller
@RequestMapping("/addmeal")
public class AddMealController {

    private AddMealService addMealService;
    private UserRepository userRepository;
    private HttpServletRequest request;

    public AddMealController(AddMealService addMealService, UserRepository userRepository, HttpServletRequest request) {
        this.addMealService = addMealService;
        this.userRepository = userRepository;
        this.request = request;
    }

    @ModelAttribute("meal")
    Meal meal() {
        return new Meal();
    }

    @GetMapping
    String createAMeal(Model model, Meal meal) {
        model.addAttribute("meal", meal);
        return "addmeal";
    }

    @ModelAttribute("users")
    List<AppUser> users() {
        return userRepository.findAll();
    }

    @ModelAttribute("newUser")
    AppUser newUser() {
        return new AppUser();
    }

    @ModelAttribute("newIngredient")
    Ingredient newIngredient() {
        return new Ingredient();
    }

    @ModelAttribute("addNewMeal")
    Meal addNewMeal() {
        return new Meal();
    }

    @PostMapping
    String createNewMeal(Meal meal, @Validated Ingredient ingredient, @RequestParam("file") MultipartFile file,
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
        addMealService.createMeal(meal);
        return "redirect:/";
    }


}