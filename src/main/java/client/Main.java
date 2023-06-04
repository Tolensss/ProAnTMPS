package client;

import domain.*;
import utilities.*;

public class Main {
    public static void main(String[] args) {
        OrderProcessingSystem orderProcessingSystem = OrderProcessingSystem.getInstance();
        System.out.println("Order processing system instantiated.");

        FoodOrder order1 = new FoodOrderBuilder()
                .setFoodItem("Pizza")
                .setQuantity(2)
                //.setSpecialInstructions("Extra cheese")
                .build();

        System.out.println("Food order created: " + order1.getFoodItem() + " (Quantity: " + order1.getQuantity() + ")");

        FoodOrder order2 = new FoodOrderBuilder()
                .setFoodItem("Burger")
                .setQuantity(1)
                .setSpecialInstructions("No onions")
                .build();

        System.out.println("Food order created: " + order2.getFoodItem() + " (Quantity: " + order2.getQuantity() + ")");

        FoodOrderDecorator decoratedOrder = new FoodOrderDecorator(order2) {
            @Override
            public String getFoodItem() {
                return super.getFoodItem() + " with extra bacon";
            }
        };

        System.out.println("Decorated food order: " + decoratedOrder.getFoodItem());

        FoodOrderService realService = new FoodOrderServiceImpl();
        FoodOrderService proxy = new FoodOrderServiceProxy(realService, "admin");
        proxy.placeOrder(order1);
        System.out.println("Order 1 placed via proxy.");

        proxy.placeOrder(decoratedOrder);
        System.out.println("Decorated order placed via proxy.");

        OrderStatusSubject orderStatusSubject = new OrderStatusSubject();
        OrderStatusObserver observer = new OrderStatusObserver() {
            @Override
            public void update(String orderStatus) {
                System.out.println("Order status updated: " + orderStatus);
            }
        };
        orderStatusSubject.attach(observer);
        orderStatusSubject.notifyObservers();
        System.out.println("Observer notified about the order status.");

        PaymentStrategy paymentStrategy = new PayPalPaymentStrategy("john.doe@example.com", "secretpassword");
        paymentStrategy.pay(25.0);
        System.out.println("Payment executed using PayPal strategy.");

        OrderCommand addItemCommand = new AddItemCommand(order1, "Dessert");
        addItemCommand.execute();
        System.out.println("Item added to order 1 using command pattern.");

        OrderCommand removeItemCommand = new RemoveItemCommand(order1, "Pizza");
        removeItemCommand.execute();
        System.out.println("Pizza removed from order 1 using command pattern.");
    }
}
