package at.refugeesCode.kitchencheffrontend.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/images/**",
                "/css/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/images/",
                        "classpath:/static/css/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login?error").setViewName("error");
    }

}

/*package at.refugeesCode.kitchencheffrontend.configuration;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        //registry.addViewController("/").setViewName("index");
        //registry.addViewController("/detail").setViewName("detail");
        //registry.addViewController("/addmeal").setViewName("addmeal");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login?error").setViewName("error");
    }

}*/
