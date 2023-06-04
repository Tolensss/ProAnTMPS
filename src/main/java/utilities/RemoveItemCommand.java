package utilities;

import domain.FoodOrder;
import utilities.OrderCommand;

public class RemoveItemCommand implements OrderCommand {
    private FoodOrder order;
    private String itemToRemove;

    private String removedItem; // To store the removed item temporarily

    public RemoveItemCommand(FoodOrder order, String itemToRemove) {
        this.order = order;
        this.itemToRemove = itemToRemove;
    }

    @Override
    public void execute() {
        // Check if the item exists in the order
        if (orderContainsItem(itemToRemove)) {
            // Remove the item from the order
            removedItem = removeItemFromOrder(itemToRemove);
            System.out.println("Item removed from order: " + removedItem);
        } else {
            System.out.println("Item not found in the order: " + itemToRemove);
        }
    }

    @Override
    public void undo() {
        // Check if there is a previously removed item
        if (removedItem != null) {
            // Add the item back to the order
            addItemToOrder(removedItem);
            System.out.println("Item added back to order: " + removedItem);
            removedItem = null; // Reset the removed item
        }
    }

    private boolean orderContainsItem(String item) {
        // Check if the order contains the specified item
        // Implementation logic
        return order.getFoodItem().equals(item);
    }

    private String removeItemFromOrder(String item) {
        // Remove the specified item from the order
        // Implementation logic
        String removedItem = order.getFoodItem();
        order.setFoodItem("No item"); // Placeholder value
        return removedItem;
    }

    private void addItemToOrder(String item) {
        // Add the specified item back to the order
        // Implementation logic
        order.setFoodItem(item);
    }
}
