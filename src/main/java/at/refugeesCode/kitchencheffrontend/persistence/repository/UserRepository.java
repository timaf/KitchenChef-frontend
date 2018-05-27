package at.refugeesCode.kitchencheffrontend.persistence.repository;

import at.refugeesCode.kitchencheffrontend.persistence.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<AppUser, String> {
    Optional<AppUser> findOneByUsername(String username);
}
