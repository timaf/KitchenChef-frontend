package at.refugeesCode.kitchencheffrontend.controller;

import at.refugeesCode.kitchencheffrontend.model.Ingredient;
import at.refugeesCode.kitchencheffrontend.model.LocalIngredient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailService {

    private RestTemplate restTemplate;

    public DetailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${meals.url}")
    private String mealsUrl;

//    For testing purposes this was turned into comment

//    @Value("${index.url}")
//    private String mainUrl;

    @Value("${detail.url}")
    private String detailUrl;

    @Value("${ingredients.url}")
    private String ingredientsUrl;

    @Value("${backend.url}")
    private String mainUrl;

    public List<LocalIngredient> getIngredients(String id) {
        List<Ingredient> ingredients = Arrays.asList(restTemplate.getForObject(mainUrl + detailUrl + ingredientsUrl + "/" + id, Ingredient.class));
        return ingredients.stream()
                .map(ingredient -> {
                    LocalIngredient localIngredient = new LocalIngredient();
                    localIngredient.setName(ingredient.getName());
                    localIngredient.setQuantity(ingredient.getQuantity());
                    localIngredient.setUnit(ingredient.getUnit());
                    return localIngredient;
                })
                .collect(Collectors.toList());
    }
}
