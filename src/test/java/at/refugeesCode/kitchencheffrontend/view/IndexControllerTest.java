package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.persistence.model.Ingredient;
import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import at.refugeesCode.kitchencheffrontend.persistence.repository.MealRepository;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import at.refugeesCode.kitchencheffrontend.service.IndexService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class IndexControllerTest {

       private MockMvc mockMvc;

        @Mock
        private IndexService indexServiceMock;
        private UserRepository userRepository;
        private PasswordEncoder passwordEncoder;

    @BeforeEach
        public void setUp() {

            mockMvc = MockMvcBuilders.standaloneSetup(new IndexController(indexServiceMock,userRepository,passwordEncoder)).build();

        }

        @Test
        void page() {
            Ingredient ingredient1 = new Ingredient("salt", 0.5, "g");
            Ingredient ingredient2 = new Ingredient("egg", 0.53, "piece");
            Ingredient ingredient3 = new Ingredient("water", 1.0, "liter");
            List<Ingredient> ingredients = new LinkedList <Ingredient>();
            ingredients.addAll(Arrays.asList(ingredient1, ingredient2, ingredient3));
            List<String> attendees = new LinkedList <String>();
            attendees.addAll(Arrays.asList("Sami", "Salwa", "Mosleh", "Suha", "Tamara"));
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date2 = LocalDate.parse("2018-07-15", df);
            Meal meal2 = new Meal("2","Nart","Soup","with cheese",ingredients,"2:50","6:10","0.30",date2,"Sabina","Gaith","Sadek",attendees);
            meal2.setNumberOfPeople(5);
            when(indexServiceMock.showUpdatedEvents()).thenReturn(Arrays.asList(meal2));

            try {
                mockMvc.perform(get("/"))
                        .andExpect(status().isOk())
                        .andExpect(view().name("index"))
                        .andExpect(model().attribute("ourMeals",hasSize(1)))
                        .andExpect(model().attribute("ourMeals",hasItem(allOf(
                                hasProperty("id",is("2")),
                                hasProperty("cookName",is("Nart")),
                                hasProperty("cleaner",is("Sabina"))
                                        )
                        )));
            } catch (Exception e) {
                e.printStackTrace();
            }

            verify(indexServiceMock, times(1)).showUpdatedEvents();
            verifyNoMoreInteractions(indexServiceMock);

        }

    }