package christmas.service;

import christmas.domain.Order;

public class OrderService {
    private Order userOrder;

    public Order order(Order order) {
        this.userOrder = order;

        // 할인과 이벤트들 적용해서 반환
        return userOrder;
    }


}
