package christmas.service;

import christmas.domain.Category;
import christmas.domain.EventBadge;
import christmas.domain.Order;

import static christmas.domain.Event.*;
import static christmas.service.SaleConfig.*;

public class OrderService {
    private Order userOrder;

    public Order order(Order order) {
        this.userOrder = order;

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

        if (isWeekend(date)) {  // 주말할인
            userOrder.addEvent(WEEKEND, WEEKEND_MAIN * userOrder.findMenuCountByCategory(Category.MAIN));
        } else if (!isWeekend(date)) {    // 평일할인
            userOrder.addEvent(WEEKDAY, WEEKDAYS_DESSERT * userOrder.findMenuCountByCategory(Category.DESSERT));
        }

        if (isStar(date)) { // 특별 할인
            userOrder.addEvent(SPECIAL, SPECIAL_STAR);
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
