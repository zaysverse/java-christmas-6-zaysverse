package christmas.domain;

public class OrderMenu {

    private Menu menu;
    private int count;
    private Long orderPrice;

    public Long getOrderPrice() {
        return Long.valueOf(menu.getPrice() * count);
    }
}
