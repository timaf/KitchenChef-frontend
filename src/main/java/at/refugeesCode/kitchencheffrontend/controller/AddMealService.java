package at.refugeesCode.kitchencheffrontend.controller;


import at.refugeesCode.kitchencheffrontend.model.Meal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AddMealService {

    private RestTemplate restTemplate;

    @Value("${meals.url}")
    private String mealsUrl;

    public AddMealService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Meal[] mealsList(){
        ResponseEntity<Meal[]> forEntity = restTemplate.getForEntity(mealsUrl, Meal[].class);
        return forEntity.getBody();
    }

    public void createMeal(Meal meal) {
        restTemplate.postForObject(mealsUrl, meal, Meal.class);
    }
}