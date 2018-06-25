package at.refugeesCode.kitchencheffrontend.view;


import at.refugeesCode.kitchencheffrontend.exception.MealNotFoundException;
import at.refugeesCode.kitchencheffrontend.persistence.model.Ingredient;
import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import at.refugeesCode.kitchencheffrontend.persistence.repository.MealRepository;
import at.refugeesCode.kitchencheffrontend.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class DetailControllerTest {

    private MockMvc mockMvc;
    private List<Ingredient> ingredients;
    private List<String> attendees;
    private LocalDate date;
    private Meal meal;
    private MealRepository mealRepositoryMock;
    private UserRepository userRepositoryMock;
    private DetailController detailController;

    @BeforeEach
    public void setUp() {
        mealRepositoryMock = mock(MealRepository.class);
        userRepositoryMock = mock(UserRepository.class);

        Ingredient ingredient1 = new Ingredient("sugar",0.5,"g");
        Ingredient ingredient2 = new Ingredient("egg",2.0,"piece");
        Ingredient ingredient3 = new Ingredient("milk",1.5,"liter");
        ingredients = new LinkedList <Ingredient>();
        this.ingredients.addAll(Arrays.asList(ingredient1,ingredient2,ingredient3));
        attendees = new LinkedList<String>();
        this.attendees.addAll(Arrays.asList("Sami","Fatima","Mosleh","Suha","Tamara"));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse("2018-07-15",df);
        this.meal = new Meal("1","Samar","cake","tasty",ingredients,"2:0","3:30","1.0",date,"Rami","kasem","Hani",attendees);

    }


    @Test
    void detailPage() {
        when(mealRepositoryMock.findById("1")).thenReturn(Optional.of(meal));
            try {
                 mockMvc.perform(get("/{id}", "1"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("detail"))
                    .andExpect(model().attribute("mealdetail", hasProperty("id", is("1"))))
                    .andExpect(model().attribute("mealdetail", hasProperty("cookName", is("Samar"))))
                    .andExpect(model().attribute("mealdetail", hasProperty("mealName", is("cake"))))
                    .andExpect(model().attribute("ingredients", hasProperty("name", is("egg"))))
                    .andExpect(model().attribute("ingredients", hasProperty("quantity", is("piece"))));

            verify(mealRepositoryMock, times(1)).findById("1");
            verifyNoMoreInteractions(mealRepositoryMock);
        } catch (Exception e) {
            e.printStackTrace();
        }
}

    @Test
    void saveAttendance() {
        String attendance = "Fatima";
        OngoingStubbing <Optional <Meal>> optionalOngoingStubbing = when(mealRepositoryMock.findById("1")).thenReturn(Optional.of(meal));

    }

    @Test
    void saveCleaner() {
    }

    @Test
    void saveHelper() {
    }

    @Test
    void saveShoper() {
    }

    @Test
    void save() {
    }
}