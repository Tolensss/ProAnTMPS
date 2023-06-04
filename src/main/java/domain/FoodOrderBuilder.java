package domain;

// Builder
public class FoodOrderBuilder {
    private String foodItem;
    private int quantity;
    private String specialInstructions;

    public FoodOrderBuilder setFoodItem(String foodItem) {
        this.foodItem = foodItem;
        return this;
    }

    public FoodOrderBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public FoodOrderBuilder setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
        return this;
    }

    public FoodOrder build() {
        return new FoodOrder(foodItem, quantity, specialInstructions);
    }
}