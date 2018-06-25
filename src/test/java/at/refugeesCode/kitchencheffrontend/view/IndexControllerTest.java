package at.refugeesCode.kitchencheffrontend.view;

import at.refugeesCode.kitchencheffrontend.persistence.model.Ingredient;
import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import at.refugeesCode.kitchencheffrontend.persistence.repository.MealRepository;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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
        private List<Ingredient> ingredients;
        private List<String> attendees;

        @Mock
        private MealRepository mealRepositoryMock;
        @Autowired
        private UserRepository userRepository;

        @BeforeEach
        public void setUp() {
            Ingredient ingredient1 = new Ingredient("sugar", 0.5, "g");
            Ingredient ingredient2 = new Ingredient("egg", 0.53, "piece");
            Ingredient ingredient3 = new Ingredient("milk", 1.0, "liter");
            ingredients = new LinkedList <Ingredient>();
            this.ingredients.addAll(Arrays.asList(ingredient1, ingredient2, ingredient3));
            attendees = new LinkedList <String>();
            this.attendees.addAll(Arrays.asList("Sami", "Fatima", "Mosleh", "Suha", "Tamara"));

        }

        @Test
        void page() {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date1 = LocalDate.parse("2018-07-15", df);
            LocalDate date2 = LocalDate.parse("2017-07-15", df);
            Meal meal1 = new Meal("1","Samar","cake","tasty",ingredients,"2:0","3:30","1.0",date1,"Rami","kasem","Hani",attendees);
            Meal meal2 = new Meal("2","Janti","Soup","with cheese",ingredients,"5:50","6:10","0.30",date2,"Sabina","Gaith","Hadi",attendees);
            when(mealRepositoryMock.findAll()).thenReturn(Arrays.asList(meal1,meal2));
            try {
                mockMvc.perform(get("/mealdetail/{id}", "1"))
                        .andExpect(status().isOk())
                        .andExpect(view().name("index"))
                        .andExpect(model().attribute("ourMeals",hasSize(1)))
                        .andExpect(model().attribute("ourMeals",hasItem(allOf(
                                hasProperty("id",is("1")),
                                hasProperty("cookName",is("Samar")),
                                hasProperty("numberOfPeople",is("5"))
                                        )
                        )));

                verify(mealRepositoryMock, times(1)).findAll();
                verifyNoMoreInteractions(mealRepositoryMock);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }