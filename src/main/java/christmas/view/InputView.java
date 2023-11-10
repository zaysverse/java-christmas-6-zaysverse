package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.repository.MenuRepository;

import java.util.HashMap;
import java.util.Map;

import static camp.nextstep.edu.missionutils.Console.*;

public class InputView {

    private static InputView inputView;

    public static InputView getInstance() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = readLine();
        return Integer.parseInt(input);
    }

    public Map<String, Integer> readOrder() {
        Map<String, Integer> orders = new HashMap<>();
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = readLine();

        for (String splits : input.split(",")) {
            String[] split = splits.split("-");
            orders.put(split[0], Integer.valueOf(split[1]));


        }
        return orders;
    }
}
