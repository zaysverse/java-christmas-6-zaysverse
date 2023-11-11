package christmas.domain;

import java.util.*;

public class Order {

    private int orderDate;
    private Map<Menu, Integer> orderMenus = new HashMap<>();
    private Long totalPrice;
    private Long discountPrice;
    private EventBadge badge; // NONE, STAR, TREE, SANTA
    private Map<Event, Long> events = new EnumMap<Event, Long>(Event.class); // CHRISTMAS_D_DAY, WEEKDAY, WEEKEND, SPECIAL, GIFT
    private boolean isGift = false;

    protected Order() {
        this.totalPrice = 0L;
        this.discountPrice = 0L;
    }

    public void addOrderMenu(Menu menu, int count) {
        orderMenus.put(menu, count);
        totalPrice += menu.getPrice() * count;
    }

    public void addEvent(Event event, Long price) {
        events.put(event, price);
        discountPrice += price;
    }

    public void setOrderDate(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("날짜는 1~31 사이의 숫자");
        }
        this.orderDate = date;
    }

    public void setGift(boolean isGift) {
        this.isGift = isGift;
    }

    public void addDiscount(Long price) {
        discountPrice += price;
    }

    public void setBadge(EventBadge badge) {
        this.badge = badge;
    }

    public int getOrderDate() {
        return orderDate;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public Long getDiscountPrice() { return discountPrice; }

    public Map<Menu, Integer> getOrderMenus() {
        return Collections.unmodifiableMap(orderMenus);
    }

    public Map<Event, Long> getEvents() {
        return Collections.unmodifiableMap(events);
    }

    public EventBadge getBadge() {
        return badge;
    }

    public boolean isGift() {
        return isGift;
    }

    // -- 생성 메서드 -- //
    public static Order createOrder(int orderDate) {
        Order order = new Order();
        order.setOrderDate(orderDate);
        order.setBadge(EventBadge.NONE);
        return order;
    }

    public int findMenuCountByCategory(Category category) {
        int count = 0;
        for (Map.Entry<Menu, Integer> orderMenu : orderMenus.entrySet()) {
            if (orderMenu.getKey().getCategory() == category) {
                count++;
            }
        }
        return count;
    }

}
