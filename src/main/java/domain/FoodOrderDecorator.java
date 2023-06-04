package domain;

import domain.FoodOrder;

// Decorator
public abstract class FoodOrderDecorator extends FoodOrder {
    private FoodOrder decoratedOrder;

    public FoodOrderDecorator(FoodOrder decoratedOrder) {
        super(decoratedOrder.getFoodItem(), decoratedOrder.getQuantity(), decoratedOrder.getSpecialInstructions());
        this.decoratedOrder = decoratedOrder;
    }

    // Additional functionality and customization methods
}
