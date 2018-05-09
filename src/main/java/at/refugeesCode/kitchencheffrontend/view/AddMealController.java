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

@Controller
@RequestMapping("/addMeal")
public class AddMealController {

    private AddMealService addMealService;

    public AddMealController(AddMealService addMealService) {
        this.addMealService = addMealService;
    }

    @ModelAttribute("meal")
    Meal meal() {
        return new Meal();
    }

    @GetMapping
    String page() {
        return "addMeal";
    }

    @PostMapping
    String post(Meal meal, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            try {
                //String UPLOADED_FOLDER = "C:\\Users\\Mohammad\\Projects\\profiles-project-demo\\src\\main\\resources\\static\\images";
                String UPLOADED_FOLDER = "C:\\Users\\sarah\\KitchenChef-frontend\\src\\main\\resources\\static\\images";

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
        return "redirect:/";
    }

}
