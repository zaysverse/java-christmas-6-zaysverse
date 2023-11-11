package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.service.MenuService;
import christmas.service.OrderService;

import java.util.Map;

public class OrderController {

    private MenuService menuService = MenuService.getInstance();
    private OrderService orderService = new OrderService();

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
