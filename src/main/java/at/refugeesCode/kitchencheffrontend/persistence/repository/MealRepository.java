package at.refugeesCode.kitchencheffrontend.persistence.repository;

import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MealRepository extends MongoRepository<Meal, String> {

}
