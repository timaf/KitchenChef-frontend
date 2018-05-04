package at.refugeesCode.ktichencheffrontend.model;

import java.time.LocalDateTime;

public class Food {

    private String id;
    private String username;
    private String foodName;
    private String foodDescription;
    private LocalDateTime dateTime;


    public Food(String username, String foodName, String foodDescription, LocalDateTime dateTime) {
        this.username = username;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.dateTime = dateTime;
    }

    public Food() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public String toString() {
        return "Food{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodDescription='" + foodDescription + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
