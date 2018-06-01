package at.refugeesCode.kitchencheffrontend.controller;

import at.refugeesCode.kitchencheffrontend.persistence.model.Ingredient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DetailService {

//    private RestTemplate restTemplate;
//
//    @Value("${meals.url}")
//    private String mealsUrl;
//    @Value("${index.url}")
//    private String mainUrl;
//    @Value("${detail.url}")
//    private String detailUrl;
//    @Value("${shoppiglist.url}")
//    private String shoppiglistUrl;
//    @Value("${attendantsNumber.url}")
//    private String attendantsNumberUrl;
//
//    public DetailService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public List<Ingredient> showShoppingList(String id) {
//        return Arrays.asList(restTemplate.getForObject(mainUrl + detailUrl + "/" + shoppiglistUrl + "/" + id, Ingredient[].class));
//    }

//    public int showAttendantsNumber(String id) {
//        return restTemplate.getForObject(mainUrl + detailUrl + "/" + attendantsNumberUrl + "/" + id, Integer.class);
//    }
}
