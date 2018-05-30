package at.refugeesCode.kitchencheffrontend.controller;

import at.refugeesCode.kitchencheffrontend.persistence.model.LocalIngredient;
import at.refugeesCode.kitchencheffrontend.persistence.model.Ingredient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailService {

    private RestTemplate restTemplate;
    @Value("${meals.url}")
    private String mealsUrl;
    @Value("${detail.url}")
    private String detailUrl;

//    For testing purposes this was turned into comment

//    @Value("${index.url}")
//    private String mainUrl;
    @Value("${ingredients.url}")
    private String ingredientsUrl;
    @Value("${backend.url}")
    private String mainUrl;

    public DetailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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
