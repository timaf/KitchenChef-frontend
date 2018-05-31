package at.refugeesCode.kitchencheffrontend.endpoint;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
import at.refugeesCode.kitchencheffrontend.persistence.model.AppUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UserEndpoint {

    private AddMealService addMealService;

    public UserEndpoint(AddMealService addMealService) {
        this.addMealService = addMealService;
    }

    @GetMapping(params = {"mealId"})
    List<AppUser> showAttendants(@RequestParam("mealId") String id, Model model) {
        model.addAttribute("meal", addMealService.findMeal(id).get());
        model.addAttribute("attendants", addMealService.showAttendants(id));
        return
    }

}
