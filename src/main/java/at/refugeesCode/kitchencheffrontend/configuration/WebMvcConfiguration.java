package at.refugeesCode.kitchencheffrontend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/meal/add-meal").setViewName("addMeal");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login?error").setViewName("error");
    }

}
