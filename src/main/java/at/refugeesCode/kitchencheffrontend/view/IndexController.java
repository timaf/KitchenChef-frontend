package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.model.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/")
public class IndexController {

    @Value("${user.url}")
    private String userUrl;

    @Value("${index.url}")
    private String mainUrl;


    private RestTemplate restTemplate;

    private PasswordEncoder passwordEncoder;

    public IndexController(RestTemplate restTemplate, PasswordEncoder passwordEncoder) {
        this.restTemplate = restTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    String page() {
        return "index";
    }

    @ModelAttribute("users")
    List<AppUser> users() {
        return Arrays.asList(restTemplate.getForObject(mainUrl + userUrl, AppUser[].class));
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
        restTemplate.postForEntity(mainUrl + userUrl, appUser, Void.class);
        return "redirect:/";
    }
}

