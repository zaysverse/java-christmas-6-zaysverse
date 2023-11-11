package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.ErrorMessage;
import christmas.repository.MenuRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static camp.nextstep.edu.missionutils.Console.*;
import static christmas.ErrorMessage.*;

public class InputView {

    public static final String ORDER_SEPARATOR = ",";
    public static final String MENU_SEPARATOR = "-";
    private static InputView inputView;

    Pattern pattern = Pattern.compile("^[0-9]+$");
    Matcher matcher;

    public static InputView getInstance() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    public boolean isNumber(String input) {
        matcher = pattern.matcher(input);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = readLine();
        if (!isNumber(input)) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
        return Integer.parseInt(input);
    }

    public Map<String, Integer> readOrder() {
        Map<String, Integer> orders = new HashMap<>();
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = readLine();

        if (!input.contains(",")) {
            readOrderOne(orders, input);
            return orders;
        }

        for (String splits : input.split(ORDER_SEPARATOR)) {
            readOrderOne(orders, splits);
        }
        return orders;
    }

    private void readOrderOne(Map<String, Integer> orders, String input) {
        if (!input.contains(MENU_SEPARATOR)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
        String[] readMenu = input.split(MENU_SEPARATOR);    // 0: 메뉴이름 , 1:수량
        if (!isNumber(readMenu[1])) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
        orders.put(readMenu[0], Integer.valueOf(readMenu[1]));
    }
}
