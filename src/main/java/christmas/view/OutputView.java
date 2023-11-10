package christmas.view;

import java.text.DecimalFormat;

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

    public void printMenu(String output, Long totalPrice) {
        System.out.println("<주문 메뉴>");
        System.out.println(output);
        System.out.println("<할인 전 총주문 금액>");
        printPrice(totalPrice);
    }

    public void printGift(String output) {
        System.out.println("<증정 메뉴>");
        System.out.println(output);
    }

    public void printEvent(String output) {
        System.out.println("<혜택 내역>");
        System.out.println(output);
    }

    public void printDiscount(Long price) {
        System.out.println("<총혜택 금액>");
        printPrice(price);
    }

    public void printOrderPrice(Long price) {
        System.out.println("<할인 후 예상 결제 금액>");
        printPrice(price);
    }

    public void printBadge(String output) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(output);
    }

    public void printPrice(Long price) {
        System.out.println(formatter.format(price));
    }



}