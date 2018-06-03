package at.refugeesCode.kitchencheffrontend.persistence.repository;

import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MealRepository extends MongoRepository<Meal, String> {
    @Override
    Optional<Meal> findById(String s);
}
