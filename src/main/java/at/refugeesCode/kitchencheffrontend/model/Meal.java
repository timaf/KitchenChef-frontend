package at.refugeesCode.kitchencheffrontend.model;

import java.time.Duration;
import java.time.LocalTime;

public class Meal {

        private String cookName;
        private String mealName;
        private String mealDescription;
        private String ingredients;

        private LocalTime startTime;
        private LocalTime cookTime;
        private Duration preparationTime;
        private String dateTime;


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

        public String getIngredients() {
                return ingredients;
        }

        public void setIngredients(String ingredients) {
                this.ingredients = ingredients;
        }

        public LocalTime getStartTime() {
                return startTime;
        }

        public void setStartTime(LocalTime startTime) {
                this.startTime = startTime;
        }

        public LocalTime getCookTime() {
                return cookTime;
        }

        public void setCookTime(LocalTime cookTime) {
                this.cookTime = cookTime;
        }

        public Duration getPreparationTime() {
                return preparationTime;
        }

        public void setPreparationTime(Duration preparationTime) {
                this.preparationTime = preparationTime;
        }

        public String getDateTime() {
                return dateTime;
        }

        public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
        }
}