package utilities;

import domain.FoodOrder;
import utilities.OrderCommand;

public class AddItemCommand implements OrderCommand {
    private FoodOrder order;
    private String item;

    public AddItemCommand(FoodOrder order, String item) {
        this.order = order;
        this.item = item;
    }

    @Override
    public void execute() {
        // Implementation to add the item to the order
    }

    @Override
    public void undo() {
        // Implementation to remove the item from the order
    }
}