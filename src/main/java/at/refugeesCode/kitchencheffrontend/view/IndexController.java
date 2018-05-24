package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.model.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    String page(){
        return "index";
    }
    @ModelAttribute("appUser")
    AppUser newUser(){
        return new AppUser();
    }


    @PostMapping
    String post(AppUser appUser){
        return "redirect:/";
    }

}

