package at.refugeesCode.kitchencheffrontend.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Meal {

    @Id
    private String id;
    private String cookName;
    private String mealName;
    private String mealDescription;
    private List<Ingredient> ingredients = new ArrayList<>();

    private int year;
    private int month;
    private int day;

    private int numberOfPeople;
    private LocalTime startCookingTime;
    private LocalTime startEatingTime;
    private Long preparationTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateTime;
    private String foodImage;
    private String cleaner;
    private String shopper;
    private String helper;
    private List<String> attendees = new ArrayList<>();

    public Meal() {
    }

    public Meal(String cookName, String mealName, String mealDescription, List <Ingredient> ingredients, int year, int month, int day, int numberOfPeople, LocalTime startCookingTime, LocalTime startEatingTime, Long preparationTime, String dateTime, String foodImage, String cleaner, String shopper, String helper, List <String> attendees) {
        this.cookName = cookName;
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.ingredients = ingredients;
        this.year = year;
        this.month = month;
        this.day = day;
        this.numberOfPeople = numberOfPeople;
        this.startCookingTime = startCookingTime;
        this.startEatingTime = startEatingTime;
        this.preparationTime = preparationTime;
        this.dateTime = dateTime;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public LocalTime getStartCookingTime() {
        return startCookingTime;
    }

    public void setStartCookingTime(LocalTime startCookingTime) {
        this.startCookingTime = startCookingTime;
    }

    public LocalTime getStartEatingTime() {
        return startEatingTime;
    }

    public void setStartEatingTime(LocalTime startEatingTime) {
        this.startEatingTime = startEatingTime;
    }

    public Long getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Long preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
                "id='" + id + '\'' +
                ", cookName='" + cookName + '\'' +
                ", mealName='" + mealName + '\'' +
                ", mealDescription='" + mealDescription + '\'' +
                ", ingredients=" + ingredients +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", numberOfPeople=" + numberOfPeople +
                ", startCookingTime=" + startCookingTime +
                ", startEatingTime=" + startEatingTime +
                ", preparationTime=" + preparationTime +
                ", dateTime='" + dateTime + '\'' +
                ", foodImage='" + foodImage + '\'' +
                ", cleaner='" + cleaner + '\'' +
                ", shopper='" + shopper + '\'' +
                ", helper='" + helper + '\'' +
                ", attendees=" + attendees +
                '}';
    }

}