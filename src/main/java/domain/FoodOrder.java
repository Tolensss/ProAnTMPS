package domain;

// Prototype
public class FoodOrder implements Cloneable {
    private String foodItem;
    private int quantity;
    private String specialInstructions;

    public FoodOrder(String foodItem, int quantity, String specialInstructions) {
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.specialInstructions = specialInstructions;
    }

    @Override
    protected FoodOrder clone() throws CloneNotSupportedException {
        return (FoodOrder) super.clone();
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
}