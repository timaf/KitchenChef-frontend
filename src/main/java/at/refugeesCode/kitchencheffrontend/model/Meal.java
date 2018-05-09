package at.refugeesCode.kitchencheffrontend.model;

import java.time.LocalTime;

public class Meal {

        private String cookName;
        private String mealName;
        private String mealDescription;
        private String ingredients;
        private String participant;
        private LocalTime mealStart;
        private LocalTime cookStart;
        private String dateTime;
        private String foodImage;


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

        public String getParticipant() {
                return participant;
        }

        public void setParticipant(String participant) {
                this.participant = participant;
        }

        public LocalTime getMealStart() {
                return mealStart;
        }

        public void setMealStart(LocalTime mealStart) {
                this.mealStart = mealStart;
        }

        public LocalTime getCookStart() {
                return cookStart;
        }

        public void setCookStart(LocalTime cookStart) {
                this.cookStart = cookStart;
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
}