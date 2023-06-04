package utilities;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusSubject {
    private List<OrderStatusObserver> observers = new ArrayList<>();
    private String orderStatus;

    public void attach(OrderStatusObserver observer) {
        observers.add(observer);
    }

    public void detach(OrderStatusObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (OrderStatusObserver observer : observers) {
            observer.update(orderStatus);
        }
    }

    // Other methods to update and retrieve order status
}