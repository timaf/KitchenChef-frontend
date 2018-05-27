package at.refugeesCode.kitchencheffrontend.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Meal {

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
    private String dateTime;
    private String foodImage;


    public Meal() {
    }

    public Meal(String cookName, String mealName, String mealDescription, List<Ingredient> ingredients, int year, int month, int day, int numberOfPeople, LocalTime startCookingTime, LocalTime startEatingTime, Long preparationTime, String dateTime, String foodImage) {
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
                '}';
    }
}