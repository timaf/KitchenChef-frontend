package at.refugeesCode.kitchencheffrontend.cotroller;


import at.refugeesCode.kitchencheffrontend.model.Meal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

<<<<<<< HEAD

=======
>>>>>>> 8c9a0ce939763ba4670c8e44c5d45715a153772e
@Service
public class AddMealService {

    private RestTemplate restTemplate;

    @Value("${meals.url}")
    private String mealsUrl;

    public AddMealService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

<<<<<<< HEAD
    public void send(Meal meal) {

    Meal meal1 = restTemplate.postForObject(kitchenUrl, meal, Meal.class);
        System.out.println(meal);
=======

    public Meal[] mealsList(){
        ResponseEntity<Meal[]> forEntity = restTemplate.getForEntity(mealsUrl, Meal[].class);
        return forEntity.getBody();
    }

    public void createMeal(Meal meal) {
        restTemplate.postForEntity(mealsUrl, meal, Meal.class);
>>>>>>> 8c9a0ce939763ba4670c8e44c5d45715a153772e
    }
}
