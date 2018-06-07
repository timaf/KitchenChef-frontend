package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.controller.AddMealService;
import at.refugeesCode.kitchencheffrontend.controller.DetailService;
import at.refugeesCode.kitchencheffrontend.persistence.model.*;
import at.refugeesCode.kitchencheffrontend.persistence.repository.MealRepository;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/detail")
public class DetailController {

    private Boolean disable;
    private AddMealService addMealService;
    private UserRepository userRepository;
    private MealRepository mealRepository;
    private DetailService detailService;
    private String mealId;
    private Boolean joind;
    private String volunteerName;

    public DetailController(AddMealService addMealService, UserRepository userRepository, MealRepository mealRepository, DetailService detailService) {
        this.addMealService = addMealService;
        this.userRepository = userRepository;
        this.mealRepository = mealRepository;
        this.detailService = detailService;
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
         mealId = id;
        disable = principal != null ? false : true;
        mealRepository.findById(id).ifPresent(meal->{
        List<Ingredient> ingredients = meal.getIngredients();
        if(principal != null) {
            volunteerName = principal.getName();
            joind = meal.getAttendees().stream().anyMatch(e -> e.equals(volunteerName));
        }
        model.addAttribute("mealdetail", meal);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("disable", disable);
        model.addAttribute("joindEating", joind);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        });
        return "detail";
    }

    @PostMapping(value="/mealdetail/{id}/signUp", params="signup=eat")
    String saveAttendance(Principal principal, Model model) {
        System.out.println("KKKKbbbbKKKKk");
        mealRepository.findById(mealId).ifPresent(meal -> {
            System.out.println("KKKKKKKKk");
            joind = meal.getAttendees().stream().anyMatch(e -> e.equals(volunteerName));
            System.out.println(joind);
            if(joind){
                meal.getAttendees().remove(volunteerName);
                joind =false;
            }else {
                meal.getAttendees().add(volunteerName);
                joind =true;
            }
            model.addAttribute("joindEating", joind);
            model.addAttribute("mealdetail", meal);
            Meal updatedMeal = save(meal);
        });
        return "detail";
    }

    @PostMapping(value="/mealdetail/{id}/signUp", params="signup=cleaner")
    String saveCleaner(Principal principal,Model model) {
         mealRepository.findById(mealId).ifPresent(meal -> {
             if(meal.getCleaner()== null){
                 meal.setCleaner(volunteerName);
                 meal.getAttendees().add(volunteerName);
             }else if (meal.getCleaner().equals(volunteerName)){
                 meal.setCleaner(null);
                 meal.getAttendees().remove(volunteerName);
             }else {
             }
            model.addAttribute("mealdetail", meal);
            Meal updatedMeal = save(meal);
         });
        return "detail";
    }

    @PostMapping(value="/mealdetail/{id}/signUp", params="signup=helper")
    String saveHelper(Principal principal, Model model) {
        mealRepository.findById(mealId).ifPresent(meal -> {
            if(meal.getHelper()== null){
                meal.setHelper(volunteerName);
                meal.getAttendees().add(volunteerName);
            }else if (meal.getHelper().equals(volunteerName)){
                meal.setHelper(null);
                meal.getAttendees().remove(volunteerName);
            }else {
            }
            model.addAttribute("mealdetail", meal);
            Meal updatedMeal = save(meal);
        });
        return "detail";
    }

    @PostMapping(value="/mealdetail/{id}/signUp", params="signup=shopper")
    String saveShoper(Principal principal, Model model) {
        mealRepository.findById(mealId).ifPresent(meal -> {
            if(meal.getShopper()== null){
                meal.setShopper(volunteerName);
                meal.getAttendees().add(volunteerName);
            }else if (meal.getShopper().equals(volunteerName)){
                meal.setShopper(null);
                meal.getAttendees().remove(volunteerName);
            }else {
             }

            model.addAttribute("mealdetail", meal);
            Meal updatedMeal = save(meal);
        });
        return "detail";
    }

    @Transactional
    public Meal save(Meal meal) {
        return mealRepository.save(meal);
    }


   /* @PostMapping("/registration/{userId}/{mealId}")
    String registration(@PathVariable("userId") String userId ,@PathVariable("mealId") String mealId, Model model){
        Idess idess = new Idess();

        Optional<AppUser> byId = userRepository.findById(userId);
        Meal meal = addMealService.detailPage(mealId);

       /* String meal_id = meal.getId();
        List<Attendees> attendants = meal.getAttendants();

        if (byId.isPresent()){
            List<Idess> mealRegistration = byId.get().getMealRegistration();

            if (!mealRegistration.isEmpty()){

                for (int i = 0; i <= mealRegistration.size(); i++){

                    if(mealRegistration.get(i) != null)
                        if(mealRegistration.get(i).getId().equalsIgnoreCase(meal_id)){

//                        ------------ Deregister meal Registration -----------------
                            mealRegistration.remove(mealRegistration.get(i));
                            byId.get().setMealRegistration(mealRegistration);
                            userRepository.save(byId.get());
//                        ------------ Deregister meal Registration -----------------
                            if (!meal.getAttendants().isEmpty()){
//                              iterate over each attendants id and check if he exist in the Attendants list
                                for (int j = 0; j<meal.getAttendants().size(); j++){
//                                    Create new Attendant if is not already exist in the Attendants list
                                    if(!meal.getAttendants().get(j).getId().equalsIgnoreCase(byId.get().getUsername())){
//                        ---------------- Create new Attendants ----------
                                        Attendees attendees = new Attendees();
                                        attendees.setId(byId.get().getId());
                                        attendees.setUsername(byId.get().getUsername());
                                        attendants.add(attendees);
                                        meal.setAttendants(attendants);
                                        mealRepository.save(meal);
//                        ---------------- Create new Attendants ----------
                                    }
                                    else {
//                        ------------ Deregister from Attendants -----------------
                                        attendants.remove(meal.getAttendants().get(j));
                                        meal.setAttendants(attendants);
                                        mealRepository.save(meal);
//                        ------------ Deregister Attendants -----------------

                                    }
                                }
                            }else {
//                        ---------------- Create new Attendants ----------
                                Attendees attendees = new Attendees();
                                attendees.setId(byId.get().getId());
                                attendees.setUsername(byId.get().getUsername());
                                attendants.add(attendees);
                                meal.setAttendants(attendants);
                                mealRepository.save(meal);
//                        ---------------- Create new Attendants ----------
                            }
                        }
                        else {
                            idess.setId(meal_id);
                            mealRegistration.add(idess);
                            byId.get().setMealRegistration(mealRegistration);
                            userRepository.save(byId.get());
                            if (!meal.getAttendants().isEmpty()){
//                              iterate over each attendants id and check if he exist in the Attendants list
                                for (int j = 0; j<meal.getAttendants().size(); j++){
//                                    Create new Attendant if is not already exist in the Attendants list
                                    if(!meal.getAttendants().get(j).getId().equalsIgnoreCase(byId.get().getUsername())){
//                        ---------------- Create new Attendants ----------
                                        Attendees attendees = new Attendees();
                                        attendees.setId(byId.get().getId());
                                        attendees.setUsername(byId.get().getUsername());

                                        attendants.add(attendees);
                                        meal.setAttendants(attendants);
                                        mealRepository.save(meal);
//                        ---------------- Create new Attendants ----------
                                    }
                                    else {
//                        ------------ Deregister from Attendants-----------------
                                        attendants.remove(meal.getAttendants().get(j));
                                        meal.setAttendants(attendants);
                                        mealRepository.save(meal);
//                        ------------ Deregister Attendants -----------------

                                    }
                                }
                            }else {
//                        ---------------- Create new Attendants ----------
                                Attendees attendees = new Attendees();
                                attendees.setId(byId.get().getId());
                                attendees.setUsername(byId.get().getUsername());
                                attendants.add(attendees);
                                meal.setAttendants(attendants);
                                mealRepository.save(meal);
//                        ---------------- Create new Attendants ----------
                            }
                        }
                }
            }
            else {
                idess.setId(meal_id);
                mealRegistration.add(idess);
                byId.get().setMealRegistration(mealRegistration);
                userRepository.save(byId.get());
                if (!meal.getAttendants().isEmpty()){
//                  iterate over each attendants id and check if he exist in the Attendants list
                    for (int j = 0; j<meal.getAttendants().size(); j++){
//                      Create new Attendant if is not already exist in the Attendants list
                        if(!meal.getAttendants().get(j).getId().equalsIgnoreCase(byId.get().getUsername())){
                            Attendees attendees = new Attendees();
                            attendees.setId(byId.get().getId());
                            attendees.setUsername(byId.get().getUsername());
                            attendants.add(meal.getAttendants().get(j));
                            meal.setAttendants(attendants);
                            mealRepository.save(meal);
                        }
                        else {
//                        ------------ Deregister from Attendants should be bottom here -----------------
                            attendants.remove(meal.getAttendants().get(j));
                            meal.setAttendants(attendants);
                            mealRepository.save(meal);
//                        ------------ Deregister Attendants should be top here -----------------

                        }
                    }
                }else {
                    Attendees attendees = new Attendees();
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
    }*/
}
