package at.refugeesCode.kitchencheffrontend.service;

import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import at.refugeesCode.kitchencheffrontend.persistence.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexService {
    private MealRepository mealRepository;

    public IndexService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> showUpdatedEvents(){
        List <Meal> meals = mealRepository.findAll();
        List <Meal> ourMeals = meals.stream().filter(meal -> meal.getMealDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());

        ourMeals.stream().forEach(meal -> meal.setNumberOfPeople(meal.getAttendees().size()));
        ourMeals.stream().forEach(meal -> mealRepository.save(meal));
        return ourMeals;
    }
}
