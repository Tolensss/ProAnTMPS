package utilities;

// Singleton
public class OrderProcessingSystem {
    private static OrderProcessingSystem instance;

    private OrderProcessingSystem() {
        // Private constructor to prevent instantiation
    }

    public static synchronized OrderProcessingSystem getInstance() {
        if (instance == null) {
            instance = new OrderProcessingSystem();
        }
        return instance;
    }

    // Other methods and functionality for the order processing system
}