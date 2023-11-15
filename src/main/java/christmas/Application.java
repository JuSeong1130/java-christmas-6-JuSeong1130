package christmas;

import christmas.config.AppConfig;
import christmas.controller.OrderController;

public class Application {

    public static void main(String[] args) {
        OrderController orderController = AppConfig.createOrderController();
        orderController.begin();
    }
}
