package at.refugeesCode.kitchencheffrontend.service;

import at.refugeesCode.kitchencheffrontend.persistence.model.Ingredient;
import at.refugeesCode.kitchencheffrontend.persistence.model.Meal;
import at.refugeesCode.kitchencheffrontend.persistence.repository.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class IndexServiceTest {

    private IndexService indexService;
    private MealRepository mealRepositoryMock;

    @BeforeEach
    public void setUP(){
        mealRepositoryMock = mock(MealRepository.class);
        indexService = new IndexService(mealRepositoryMock);
    }

    @Test
    void showUpdatedEvents() {
        Ingredient ingredient1 = new Ingredient("sugar", 0.5, "g");
        Ingredient ingredient2 = new Ingredient("egg", 0.53, "piece");
        Ingredient ingredient3 = new Ingredient("milk", 1.0, "liter");
        List<Ingredient> ingredients = new LinkedList<Ingredient>();
        ingredients.addAll(Arrays.asList(ingredient1, ingredient2, ingredient3));
        List<String> attendees = new LinkedList <String>();
        attendees.addAll(Arrays.asList("Sami", "Fatima", "Mosleh", "Suha", "Tamara"));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse("2018-09-15", df);
        LocalDate date2 = LocalDate.parse("2017-07-15", df);
        Meal meal1 = new Meal("1","Samar","cake","tasty",ingredients,"2:0","3:30","1.0",date1,"Rami","kasem","Hani",attendees);
        Meal meal2 = new Meal("2","Janti","Soup","with cheese",ingredients,"5:50","6:10","0.30",date2,"Sabina","Gaith","Hadi",attendees);

        when(mealRepositoryMock.findAll()).thenReturn(Arrays.asList(meal1,meal2));

        List <Meal> actual = indexService.showUpdatedEvents();

        List <Meal> expectedMeals = new LinkedList <>();
        expectedMeals.add(meal1);

        assertThat(actual,is(expectedMeals));

        verify(mealRepositoryMock,times(1)).findAll();
    }
}