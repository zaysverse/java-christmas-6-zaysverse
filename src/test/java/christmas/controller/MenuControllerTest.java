package christmas.controller;

import christmas.service.MenuService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MenuControllerTest {

    @Test
    public void init() throws Exception {
        MenuController menuController = MenuController.getInstance();
        MenuService menuService = MenuService.getInstance();

        assertThat(menuService.findMenu("레드와인").getPrice()).isEqualTo(60000);
    }

}