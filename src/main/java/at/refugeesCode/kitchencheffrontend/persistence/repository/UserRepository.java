package at.refugeesCode.kitchencheffrontend.persistence.repository;

import at.refugeesCode.kitchencheffrontend.persistence.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends MongoRepository<AppUser, String> {
    Optional<AppUser> findOneByUsername(String username);

    @Override
    Optional<AppUser> findById(String s);
}
