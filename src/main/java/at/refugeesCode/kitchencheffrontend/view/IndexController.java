package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.persistence.model.AppUser;
import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import at.refugeesCode.kitchencheffrontend.persistence.repository.MealRepository;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/")
public class IndexController {

    private MealRepository mealRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public IndexController(MealRepository mealRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("newUser")
    AppUser newUser() {
        return new AppUser();
    }

    @PostMapping("/register")
    String post(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setAuthorities(Stream.of("USER").collect(Collectors.toSet()));
        userRepository.save(appUser);
        return "redirect:/";
    }

    @GetMapping
    String page(Model model) {
        List <Meal> meals = mealRepository.findAll();
        List <Meal> ourMeals = meals.stream().filter(meal -> meal.getMealDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());

        ourMeals.stream().forEach(meal -> meal.setNumberOfPeople(meal.getAttendees().size()));
        ourMeals.stream().forEach(meal -> mealRepository.save(meal));
        model.addAttribute("ourMeals", ourMeals);
        return "index";
    }
}