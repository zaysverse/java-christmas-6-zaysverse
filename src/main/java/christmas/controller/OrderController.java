package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.service.MenuService;
import christmas.service.OrderService;
import christmas.view.InputView;

import java.time.DateTimeException;
import java.util.Map;

public class OrderController {

    private static OrderController orderController;

    private InputView inputView = InputView.getInstance();
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

    public Order create(int date) {
        Order order = Order.createOrder(date);
        return orderService.order(order);
    }

    public Order update(Order order, Map<String, Integer> readOrders) throws RuntimeException {
        for (Map.Entry<String, Integer> readOrder : readOrders.entrySet()) {
            Menu menu = menuService.findMenu(readOrder.getKey());
            int count = readOrder.getValue();
            order.addOrderMenu(menu, count);
        }
        order.validate();
        return order;
    }
}
