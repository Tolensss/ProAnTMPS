package client;

import domain.*;
import utilities.*;

import java.util.Scanner;

public class OrderInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the food item: ");
        String foodItem = scanner.nextLine();

        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline character

        System.out.print("Enter any special instructions: ");
        String specialInstructions = scanner.nextLine();

        FoodOrder order = new FoodOrderBuilder()
                .setFoodItem(foodItem)
                .setQuantity(quantity)
                .setSpecialInstructions(specialInstructions)
                .build();

        System.out.println("Your order: " + order.getFoodItem() +
                " (Quantity: " + order.getQuantity() +
                ", Special Instructions: " + order.getSpecialInstructions() + ")");

        OrderProcessingSystem orderProcessingSystem = OrderProcessingSystem.getInstance();
        System.out.println("Order processing system instantiated.");

        FoodOrderDecorator decoratedOrder = new FoodOrderDecorator(order) {
            @Override
            public String getFoodItem() {
                return super.getFoodItem() + " with toppings";
            }
        };

        System.out.println("Decorated food order: " + decoratedOrder.getFoodItem());

        FoodOrderService realService = new FoodOrderServiceImpl();
        FoodOrderService proxy = new FoodOrderServiceProxy(realService, "admin");
        proxy.placeOrder(order);
        System.out.println("Order placed via proxy.");

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

        System.out.print("Enter your payment method (credit card / PayPal): ");
        String paymentMethod = scanner.nextLine();

        PaymentStrategy paymentStrategy;

        if (paymentMethod.equalsIgnoreCase("credit card")) {
            System.out.print("Enter your card number: ");
            String cardNumber = scanner.nextLine();

            System.out.print("Enter the expiration date (MM/YY): ");
            String expirationDate = scanner.nextLine();

            System.out.print("Enter the CVV: ");
            String cvv = scanner.nextLine();

            paymentStrategy = new CreditCardPaymentStrategy(cardNumber, expirationDate, cvv);
        } else if (paymentMethod.equalsIgnoreCase("PayPal")) {
            System.out.print("Enter your PayPal email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your PayPal password: ");
            String password = scanner.nextLine();

            paymentStrategy = new PayPalPaymentStrategy(email, password);
        } else {
            System.out.println("Invalid payment method.");
            return;
        }



        OrderCommand addItemCommand = new AddItemCommand(order, "Beverage");
        addItemCommand.execute();
        addItemCommand.undo();
        System.out.println("Item added to order and then undone using command pattern.");
    }
}
