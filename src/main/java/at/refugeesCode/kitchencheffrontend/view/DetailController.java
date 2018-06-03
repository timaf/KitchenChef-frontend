package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
import at.refugeesCode.kitchencheffrontend.controller.DetailService;
import at.refugeesCode.kitchencheffrontend.persistence.model.*;
import at.refugeesCode.kitchencheffrontend.persistence.repository.MealRepository;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/detail")
public class DetailController {

    private Boolean disable;
    private AddMealService addMealService;
    private UserRepository userRepository;
    private MealRepository mealRepository;
    private DetailService detailService;

    public DetailController(AddMealService addMealService, UserRepository userRepository, MealRepository mealRepository, DetailService detailService) {
        this.addMealService = addMealService;
        this.userRepository = userRepository;
        this.mealRepository = mealRepository;
        this.detailService = detailService;
    }

    @ModelAttribute("users")
    List<AppUser> users() {
        return userRepository.findAll();
    }

    @ModelAttribute("newUser")
    AppUser newUser() {
        return new AppUser();
    }

    @GetMapping
    String page() {
        return "detail";
    }

    @GetMapping("/mealdetail/shoppinglist/{id}")
    String showShoppingList(@PathVariable("id") String id, Model model) {
        Meal meal = addMealService.detailPage(id);
        model.addAttribute("shoppinglist", meal.getIngredients());
        return "shoppinglist";
    }

    @GetMapping("/mealdetail/{id}")
    String detailPage(@PathVariable("id") String id, Model model, Principal principal){
        Meal meal = addMealService.detailPage(id);
        disable = principal != null ? false : true;
        List<Ingredient> ingredients = meal.getIngredients();
        List<Attendees> attendants = meal.getAttendants();
        model.addAttribute("mealdetail", meal);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("attendants", attendants);
        model.addAttribute("disable", disable);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);


        return "detail";
    }

    @PostMapping("/registration/{userId}/{mealId}")
    String registration(@PathVariable("userId") String userId ,@PathVariable("mealId") String mealId, Model model){
        Idess idess = new Idess();
        Attendees attendees = new Attendees();

        Optional<AppUser> byId = userRepository.findById(userId);
        Meal meal = addMealService.detailPage(mealId);

        String meal_id = meal.getId();
        List<Attendees> attendants = meal.getAttendants();

        if (byId.isPresent()){
            List<Idess> mealRegistration = byId.get().getMealRegistration();

            if (!mealRegistration.isEmpty()){

                for (Idess id: mealRegistration){

                    if(id.getId().equalsIgnoreCase(meal_id)){

//                        ------------ Deregister meal Registration should be bottom here -----------------
                        mealRegistration.remove(id);
                        //mealRegistration.add(idess);
                        byId.get().setMealRegistration(mealRegistration);
                        userRepository.save(byId.get());
//                        ------------ Deregister meal Registration should be top here -----------------
                        if (!attendants.isEmpty()){

                            for (Attendees oneAttendes : meal.getAttendants()){

                                if(!oneAttendes.getId().equalsIgnoreCase(byId.get().getUsername())){

                                    oneAttendes.setId(byId.get().getId());
                                    oneAttendes.setUsername(byId.get().getUsername());
                                    attendants.add(oneAttendes);
                                    meal.setAttendants(attendants);
                                    mealRepository.save(meal);
                                }
                                else {
//                        ------------ Deregister from Attendants should be bottom here -----------------
                                    attendants.remove(oneAttendes);
                                    meal.setAttendants(attendants);
                                    mealRepository.save(meal);
//                        ------------ Deregister Attendants should be top here -----------------

                                }
                            }
                        }else {
                            attendees.setId(byId.get().getId());
                            attendees.setUsername(byId.get().getUsername());
                            attendants.add(attendees);
                            meal.setAttendants(attendants);
                            mealRepository.save(meal);
                        }
                    }
                    else {
                        idess.setId(meal_id);
                        mealRegistration.add(idess);
                        byId.get().setMealRegistration(mealRegistration);
                        userRepository.save(byId.get());
                        if (!attendants.isEmpty()){

                            for (Attendees oneAttendes : meal.getAttendants()){

                                if(!oneAttendes.getId().equalsIgnoreCase(byId.get().getUsername())){

                                    oneAttendes.setId(byId.get().getId());
                                    oneAttendes.setUsername(byId.get().getUsername());
                                    attendants.add(oneAttendes);
                                    meal.setAttendants(attendants);
                                    mealRepository.save(meal);
                                }
                                else {
//                        ------------ Deregister from Attendants should be bottom here -----------------
                                    attendants.remove(oneAttendes);
                                    meal.setAttendants(attendants);
                                    mealRepository.save(meal);
//                        ------------ Deregister Attendants should be top here -----------------

                                }
                            }
                        }else {
                            attendees.setId(byId.get().getId());
                            attendees.setUsername(byId.get().getUsername());
                            attendants.add(attendees);
                            meal.setAttendants(attendants);
                            mealRepository.save(meal);
                        }
                    }
                }
            }
            else {
                idess.setId(meal_id);
                mealRegistration.add(idess);
                byId.get().setMealRegistration(mealRegistration);
                userRepository.save(byId.get());
                if (!attendants.isEmpty()){

                    for (Attendees oneAttendes : meal.getAttendants()){

                        if(!oneAttendes.getId().equalsIgnoreCase(byId.get().getUsername())){

                            oneAttendes.setId(byId.get().getId());
                            oneAttendes.setUsername(byId.get().getUsername());
                            attendants.add(oneAttendes);
                            meal.setAttendants(attendants);
                            mealRepository.save(meal);
                        }
                        else {
//                        ------------ Deregister from Attendants should be bottom here -----------------
                            attendants.remove(oneAttendes);
                            meal.setAttendants(attendants);
                            mealRepository.save(meal);
//                        ------------ Deregister Attendants should be top here -----------------

                        }
                    }
                }else {
                    attendees.setId(byId.get().getId());
                    attendees.setUsername(byId.get().getUsername());
                    attendants.add(attendees);
                    meal.setAttendants(attendants);
                    mealRepository.save(meal);
                }
            }
        }
        else {
            return "error";
        }
        return "redirect:/";
    }
}
