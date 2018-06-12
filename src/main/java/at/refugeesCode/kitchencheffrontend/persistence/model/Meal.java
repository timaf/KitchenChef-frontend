package at.refugeesCode.kitchencheffrontend.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.DATE;

@Document
public class Meal {

    @Id
    private String id;
    private String cookName;
    private String mealName;
    private String mealDescription;
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    private int numberOfPeople;
    private String startCookingTime;
    private String startEatingTime;
    private String preparationTime;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate mealDate;
    private String foodImage;
    private String cleaner;
    private String shopper;
    private String helper;
    private List<String> attendees = new ArrayList<>();

    public Meal() {
    }

    public Meal(String cookName, String mealName, String mealDescription, List <Ingredient> ingredients, int numberOfPeople, String startCookingTime, String startEatingTime, String preparationTime, LocalDate mealDate, String foodImage, String cleaner, String shopper, String helper, List <String> attendees) {
        this.cookName = cookName;
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.ingredients = ingredients;
        this.numberOfPeople = numberOfPeople;
        this.startCookingTime = startCookingTime;
        this.startEatingTime = startEatingTime;
        this.preparationTime = preparationTime;
        this.mealDate = mealDate;
        this.foodImage = foodImage;
        this.cleaner = cleaner;
        this.shopper = shopper;
        this.helper = helper;
        this.attendees = attendees;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }

    public List <Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List <Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getStartCookingTime() {
        return startCookingTime;
    }

    public void setStartCookingTime(String startCookingTime) {
        this.startCookingTime = startCookingTime;
    }

    public String getStartEatingTime() {
        return startEatingTime;
    }

    public void setStartEatingTime(String startEatingTime) {
        this.startEatingTime = startEatingTime;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getCleaner() {
        return cleaner;
    }

    public void setCleaner(String cleaner) {
        this.cleaner = cleaner;
    }

    public String getShopper() {
        return shopper;
    }

    public void setShopper(String shopper) {
        this.shopper = shopper;
    }

    public String getHelper() {
        return helper;
    }

    public void setHelper(String helper) {
        this.helper = helper;
    }

    public List <String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List <String> attendees) {
        this.attendees = attendees;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "cookName='" + cookName + '\'' +
                ", mealName='" + mealName + '\'' +
                ", mealDescription='" + mealDescription + '\'' +
                ", ingredients=" + ingredients +
                ", numberOfPeople=" + numberOfPeople +
                ", startCookingTime=" + startCookingTime +
                ", startEatingTime=" + startEatingTime +
                ", preparationTime=" + preparationTime +
                ", mealDate=" + mealDate +
                ", foodImage='" + foodImage + '\'' +
                ", cleaner='" + cleaner + '\'' +
                ", shopper='" + shopper + '\'' +
                ", helper='" + helper + '\'' +
                ", attendees=" + attendees +
                '}';
    }

}