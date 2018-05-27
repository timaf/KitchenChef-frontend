package at.refugeesCode.kitchencheffrontend.security;

import at.refugeesCode.kitchencheffrontend.persistence.model.AppUser;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<AppUser> user = userRepository.findOneByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user.get());
    }
}