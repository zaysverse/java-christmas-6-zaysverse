package christmas.service;

import christmas.domain.Category;
import christmas.domain.EventBadge;
import christmas.domain.Order;

import static christmas.ErrorMessage.INVALID_ORDER;
import static christmas.domain.Category.*;
import static christmas.domain.Event.*;
import static christmas.service.SaleConfig.*;

public class OrderService {
    public static final int EVENT_CONDI = 10000;
    public static final int MAX_MENU_CNT = 20;

    private static OrderService orderService;

    private Order userOrder;

    private OrderService() {
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderService();
        }
        return orderService;
    }

    public Order order(Order order) {
        this.userOrder = order;

        validation();

        if (order.getTotalPrice() < EVENT_CONDI) {
            return userOrder;
        }

        // 이벤트 적용
        sale(order.getOrderDate());
        giftEvent();
        giveEventBadge();

        return userOrder;
    }

    public void sale(int date) {
        if (date <= 25) { // 크리스마스 디데이 할인
            userOrder.addEvent(CHRISTMAS_D_DAY, CHRIS_START + CHRIS_EACH * (date - 1));
        }

        if (isWeekend(date) && userOrder.containsMenuByCategory(MAIN)) {  // 주말할인
            userOrder.addEvent(WEEKEND, WEEKEND_MAIN * userOrder.findMenuCountByCategory(MAIN));
        }
        if (!isWeekend(date) && userOrder.containsMenuByCategory(DESSERT)) {    // 평일할인
            userOrder.addEvent(WEEKDAY, WEEKDAYS_DESSERT * userOrder.findMenuCountByCategory(DESSERT));
        }

        if (isStar(date)) { // 특별 할인
            userOrder.addEvent(SPECIAL, SPECIAL_STAR);
        }
    }

    private void validation() {
        if (userOrder.findMenuCountByCategory(BEVERAGE) == userOrder.findAllCount()) {
            throw new IllegalStateException(INVALID_ORDER.getMessage());
        }

        if (userOrder.findAllCount() > MAX_MENU_CNT) {
            throw new IllegalStateException(INVALID_ORDER.getMessage());
        }

    }

    private boolean isWeekend(int date) {
        if ((date - 1) % 7 < 2) {
            return true;
        }
        return false;
    }

    private boolean isStar(int date) {
        if (date % 7 == 3 || date == CHRISTMAS_DAY) {
            return true;
        }
        return false;
    }

    private void giftEvent() {
        if (userOrder.getTotalPrice() >= GIFT_CONDITION) {
            userOrder.setGift(true);
            userOrder.addDiscount(GIFT_CHAMPAGNE_PRICE);
        }
    }

    private void giveEventBadge() {
        long discountPrice = userOrder.getDiscountPrice() * -1;
        if (discountPrice > BADGE_SANTA_COND) {
            userOrder.setBadge(EventBadge.SANTA);
        } else if (discountPrice > BADGE_TREE_COND) {
            userOrder.setBadge(EventBadge.TREE);
        } else if (discountPrice > BADGE_STAR_COND) {
            userOrder.setBadge(EventBadge.STAR);
        }
    }
}
