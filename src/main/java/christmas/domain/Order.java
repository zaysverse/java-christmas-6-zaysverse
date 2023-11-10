package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderMenu> orderMenus = new ArrayList<>();
    private Long totalPrice;
    private Long discountPrice;
    private int orderDate;
    private EventBadge badge; // NONE, STAR, TREE, SANTA

    protected Order() {
        this.totalPrice = 0L;
        this.discountPrice = 0L;
    }

    public void addOrderMenu(OrderMenu orderMenu) {
        orderMenus.add(orderMenu);
        totalPrice += orderMenu.getOrderPrice();
    }

    public void setOrderDate(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("날짜는 1~31 사이의 숫자");
        }
        this.orderDate = date;
    }

    public void setBadge(EventBadge badge) {
        this.badge = badge;
    }

    // -- 생성 메서드 -- //
    public static Order createOrder(int orderDate, OrderMenu... orderMenus) {
        Order order = new Order();
        for (OrderMenu orderMenu : orderMenus) {
            order.addOrderMenu(orderMenu);
        }
        order.setOrderDate(orderDate);
        order.setBadge(EventBadge.NONE);
        return order;
    }

}
