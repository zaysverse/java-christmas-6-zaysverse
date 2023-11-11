package christmas.view;

import christmas.domain.Category;
import christmas.domain.Event;
import christmas.domain.Menu;
import christmas.service.SaleConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OutputViewTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    OutputView outputView = OutputView.getInstance();
    private static ByteArrayOutputStream output;

    @BeforeEach
    void beforeEach() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    void printMenu_메뉴가_한개일때() {
        Map<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(Menu.createMenu("메뉴1", 1000, Category.MAIN), 1);
        outputView.printMenu(orderMenus);
        assertEquals("<주문 메뉴>" + LINE_SEPARATOR + "메뉴1 1개" + LINE_SEPARATOR + LINE_SEPARATOR
                , output.toString());
    }

    @Test
    void printMenu_메뉴가_두개일때() {
        Map<Menu, Integer> orderMenus = new HashMap<>();
        orderMenus.put(Menu.createMenu("메뉴1", 1000, Category.MAIN), 1);
        orderMenus.put(Menu.createMenu("메뉴2", 2000, Category.MAIN), 2);
        outputView.printMenu(orderMenus);
        assertThat(output.toString())
                .contains("메뉴1 1개" + LINE_SEPARATOR)
                .contains("메뉴2 2개" + LINE_SEPARATOR);
    }

    @Test
    void printGift_증정_없음() {
        outputView.printGift(false);
        assertEquals("<증정 메뉴>" + LINE_SEPARATOR + "없음" + LINE_SEPARATOR + LINE_SEPARATOR,
                output.toString());
    }

    @Test
    void printGift_증정_있음() {
        outputView.printGift(true);
        assertEquals("<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개" + LINE_SEPARATOR + LINE_SEPARATOR,
                output.toString());
    }

    @Test
    void printEvent_없음() {
        outputView.printEvent(new HashMap<>(), false);
        assertEquals("<혜택 내역>" + LINE_SEPARATOR + "없음" + LINE_SEPARATOR + LINE_SEPARATOR,
                output.toString());
    }

    @Test
    void printEvent_혜택_여러개_증정O() {
        Map<Event, Long> events = new HashMap<>();
        events.put(Event.CHRISTMAS_D_DAY, -13000L);
        events.put(Event.SPECIAL, -13000L);
        events.put(Event.WEEKEND, -2000L);
        outputView.printEvent(events, true);
        assertThat(output.toString())
                .contains(Event.CHRISTMAS_D_DAY.getMessage() + ": " + outputView.priceOf(-13000L))
                .contains(Event.SPECIAL.getMessage() + ": " + outputView.priceOf(-13000L))
                .contains(Event.WEEKEND.getMessage() + ": " + outputView.priceOf(-2000L));
    }


    @Test
    void priceOf() {
        assertThat(outputView.priceOf(10L)).isEqualTo("10원");
        assertThat(outputView.priceOf(1000L)).isEqualTo("1,000원");
        assertThat(outputView.priceOf(12000000L)).isEqualTo("12,000,000원");
        assertThat(outputView.priceOf(-1000L)).isEqualTo("-1,000원");
    }
}