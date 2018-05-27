package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.persistence.model.AppUser;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppUserController {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public AppUserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    String page() {
        return "index";
    }

    @ModelAttribute("users")
    List<AppUser> users() {
        return userRepository.findAll();
    }

    @ModelAttribute("newUser")
    AppUser newUser() {
        return new AppUser();
    }

    @ModelAttribute("loggedUserName")
    String principal(Principal principal) {
        return principal.getName();
    }

    @PostMapping
    String post(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setAuthorities(Stream.of("USER").collect(Collectors.toSet()));
        userRepository.save(appUser);
        return "redirect:/";
    }
}
