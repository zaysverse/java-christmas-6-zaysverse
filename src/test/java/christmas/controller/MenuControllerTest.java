package christmas.controller;

import christmas.service.MenuService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MenuControllerTest {

    @Test
    public void init_초기화시_파일을_읽어와서_저장소에_저장() throws Exception {
        MenuController menuController = MenuController.getInstance();
        MenuService menuService = MenuService.getInstance();

        assertThat(menuService.findMenu("레드와인").getPrice()).isEqualTo(60000);
    }

}