package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Category;
import christmas.domain.Menu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class InputViewTest {

    InputView inputView = InputView.getInstance();

    @AfterEach
    void setup() {
        Console.close();
    }

    @Test
    void isNumber() {
        assertTrue(inputView.isNumber("1000"));
        assertTrue(inputView.isNumber("1321419871241028930"));
        assertFalse(inputView.isNumber("12j"));
        assertFalse(inputView.isNumber("a1231"));
    }

    @Test
    void readOrder_메뉴가_한개일때() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("메뉴1", 1);

        InputStream input = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("메뉴1-1\n".getBytes());
        System.setIn(in);
        Map<String, Integer> actual = inputView.readOrder();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }


    @Test
    void readOrder_메뉴가_여러개일때() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("메뉴1", 1);
        expected.put("메뉴30", 30);

        InputStream input = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("메뉴1-1,메뉴30-30\n".getBytes());
        System.setIn(in);
        Map<String, Integer> actual = inputView.readOrder();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }


}