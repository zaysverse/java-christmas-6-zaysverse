package christmas.controller;

import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class Planner {

    private InputView inputView = InputView.getInstance();
    private OutputView outputView = OutputView.getInstance();
    private MenuController menuController = MenuController.getInstance();
    private OrderController orderController = new OrderController();

    public Planner() {
    }

    public void run() {
        Order order = init();
        outputView.printOrder(order);

    }

    public Order init() {
        int date = inputView.readDate();
        Map<String, Integer> readOrder = inputView.readOrder();
        Order order = orderController.create(date, readOrder);
        return order;
    }

}
