package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.service.MenuService;
import christmas.service.OrderService;

import java.util.Map;

public class OrderController {

    private static OrderController orderController;

    private MenuService menuService = MenuService.getInstance();
    private OrderService orderService = OrderService.getInstance();

    private OrderController() {
    }

    public static OrderController getInstance() {
        if (orderController == null) {
            orderController = new OrderController();
        }
        return orderController;
    }

    public Order create(int date, Map<String, Integer> readOrders) {
        Order order = Order.createOrder(date);

        for (Map.Entry<String, Integer> readOrder : readOrders.entrySet()) {
            Menu menu = menuService.findMenu(readOrder.getKey());
            int count = readOrder.getValue();
            order.addOrderMenu(menu, count);
        }

        orderService.order(order);

        return order;
    }
}
