package christmas.view;

import christmas.domain.Event;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.service.SaleConfig;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private static OutputView outputView;

    DecimalFormat formatter = new DecimalFormat("###,###원");

    private OutputView() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static OutputView getInstance() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public void printPreviewMessage() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public void printOrder(Order order) {
        printPreviewMessage();
        printMenu(order.getOrderMenus());
        printTotalPrice(order.getTotalPrice());
        printGift(order.isGift());
        printEvent(order.getEvents(), order.isGift());
        printDiscountPrice(order.getDiscountPrice());

        printOrderPrice(getOrderPrice(order));

        printBadge(order.getBadge().getMessage());
    }

    private long getOrderPrice(Order order) {
        if (order.isGift()) {
            return order.getTotalPrice() + order.getDiscountPrice() - SaleConfig.GIFT_CHAMPAGNE_PRICE;
        }
        return order.getTotalPrice() + order.getDiscountPrice();
    }

    public void printMenu(Map<Menu, Integer> orderMenus) {
        System.out.println("<주문 메뉴>");
        for (Map.Entry<Menu, Integer> orderMenu : orderMenus.entrySet()) {
            System.out.println(orderMenu.getKey().getName() + " " + orderMenu.getValue() + "개");
        }
        System.out.println();
    }


    public void printGift(Boolean isGift) {
        System.out.println("<증정 메뉴>");
        if (isGift) {
            System.out.println(SaleConfig.GIVEAWAY);
        } else {
            System.out.println("없음");
        }
        System.out.println();
    }

    public void printEvent(Map<Event, Long> events, boolean isGift) {
        System.out.println("<혜택 내역>");
        for (Map.Entry<Event, Long> event : events.entrySet()) {
            System.out.println(event.getKey().getMessage() + ": " + priceOf(event.getValue()));
        }
        if (isGift) {
            System.out.println("증정 이벤트: -25,000원");
        }

        if (events.isEmpty() && !isGift) {
            System.out.println("없음");
        }

        System.out.println();
    }

    public void printTotalPrice(Long price) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(priceOf(price));
        System.out.println();
    }

    public void printDiscountPrice(Long price) {
        System.out.println("<총혜택 금액>");
        System.out.println(priceOf(price));
        System.out.println();
    }

    public void printOrderPrice(Long price) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(priceOf(price));
        System.out.println();
    }

    public void printBadge(String badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
    }

    /**
     * Long -> String : "###,###원"
     */
    public String priceOf(Long price) {
        return formatter.format(price);
    }
}