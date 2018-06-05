package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
import at.refugeesCode.kitchencheffrontend.persistence.model.AppUser;
import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/")
public class IndexController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AddMealService addMealService;

    public IndexController(UserRepository userRepository, PasswordEncoder passwordEncoder, AddMealService addMealService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addMealService = addMealService;
    }

    @ModelAttribute("users")
    List<AppUser> users() {
        return userRepository.findAll();
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
        Meal[] meal = addMealService.mealsList();
        model.addAttribute("meals", meal);
        return "index";
    }
}