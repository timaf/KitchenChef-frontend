package at.refugeesCode.kitchencheffrontend.controller;

import at.refugeesCode.kitchencheffrontend.persistence.model.AppUser;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private AddMealService addMealService;

    public UserService(UserRepository userRepository, AddMealService addMealService) {
        this.userRepository = userRepository;
        this.addMealService = addMealService;
    }

    public List<AppUser> showRegisteredUsers(String id) {
        AddMealService

        return null;
    }


}
