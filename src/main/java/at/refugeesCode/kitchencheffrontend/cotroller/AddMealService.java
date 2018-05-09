package at.refugeesCode.kitchencheffrontend.cotroller;

import at.refugeesCode.kitchencheffrontend.model.Meal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalTime;

@Service
public class AddMealService {

    private RestTemplate restTemplate;

    @Value("${meal.url}")
    private String kitchenUrl;

    public AddMealService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void send(Meal meal) {
    Meal meal1 = restTemplate.postForObject(kitchenUrl, meal, Meal.class);
        System.out.println(meal);
    }
}
