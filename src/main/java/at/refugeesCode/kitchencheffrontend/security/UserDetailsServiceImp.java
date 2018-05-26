package at.refugeesCode.kitchencheffrontend.security;

import at.refugeesCode.kitchencheffrontend.model.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final RestTemplate restTemplate;

    @Value("${user.url}")
    private String userUrl;

    @Value("${index.url}")
    private String mainUrl;

    public UserDetailsServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

//        Optional<AppUser> user = userRepository.findOneByUsername(username);
//        Optional<AppUser> user = restTemplate.getForObject(mainUrl + userUrl, AppUser[].class);
//        if (!user.isPresent()) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new UserPrincipal(user.get());
        return null;
    }
}