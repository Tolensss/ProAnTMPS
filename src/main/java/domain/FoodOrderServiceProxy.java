package domain;

import domain.FoodOrder;
import domain.FoodOrderService;

public class FoodOrderServiceProxy implements FoodOrderService {
    private FoodOrderService realService;
    private String userRole;

    public FoodOrderServiceProxy(FoodOrderService realService, String userRole) {
        this.realService = realService;
        this.userRole = userRole;
    }

    @Override
    public void placeOrder(FoodOrder order) {
        if (userRole.equals("admin")) {
            realService.placeOrder(order);
        } else {
            System.out.println("Access denied. Only admins can place orders.");
        }
    }
}