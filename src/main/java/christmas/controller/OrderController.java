package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.service.MenuService;

import java.util.Map;

public class OrderController {

    private MenuService menuService = new MenuService();

    public Order create(int date, Map<String, Integer> readOrders) {
        Order order = Order.createOrder(date);

        for (Map.Entry<String, Integer> readOrder : readOrders.entrySet()) {
            Menu menu = menuService.findMenu(readOrder.getKey());
            int count = readOrder.getValue();
            order.addOrderMenu(menu, count);
        }

        return order;
    }
}
