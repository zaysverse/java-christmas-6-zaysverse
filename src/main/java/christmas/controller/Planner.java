package christmas.controller;

import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class Planner {

    private InputView inputView = InputView.getInstance();
    private OutputView outputView = OutputView.getInstance();
    private MenuController menuController = MenuController.getInstance();
    private OrderController orderController = OrderController.getInstance();

    public Planner() {}

    public void run() {
        // 주문
        Order order = planOrder();
        // 주문결과 출력
        outputView.printOrder(order);
    }

    private Order planOrder() {
        int date = inputView.readDate();
        Map<String, Integer> readOrder = inputView.readOrder();
        Order order = orderController.create(date, readOrder);
        return order;
    }
}
