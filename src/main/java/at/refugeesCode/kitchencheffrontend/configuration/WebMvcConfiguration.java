package at.refugeesCode.kitchencheffrontend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/add-meal").setViewName("addMeal");
        registry.addViewController("/detail").setViewName("detail");
        registry.addViewController("/meal/addmeal").setViewName("addmeal");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login?error").setViewName("error");
    }

}
