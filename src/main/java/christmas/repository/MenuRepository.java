package christmas.repository;

import christmas.controller.MenuController;
import christmas.domain.Category;
import christmas.domain.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    List<Menu> repository;
    MenuController menuController;

    public MenuRepository() {
        this.repository = new ArrayList<>();
        this.menuController = new MenuController();
        init();
    }

    private void init() {
        save(menuController.create("양송이수프", 6000, "APPETIZER"));
        save(menuController.create("타파스", 5500, "APPETIZER"));
        save(menuController.create("시저샐러드", 8000, "APPETIZER"));

        save(menuController.create("티본스테이크", 55000, "MAIN"));
        save(menuController.create("바비큐립", 54000, "MAIN"));
        save(menuController.create("해산물파스타", 35000, "MAIN"));
        save(menuController.create("크리스마스파스타", 25000, "MAIN"));

        save(menuController.create("초코케이크", 15000, "DESSERT"));
        save(menuController.create("아이스크림", 5000, "DESSERT"));

        save(menuController.create("제로콜라", 3000, "BEVERAGE"));
        save(menuController.create("레드와인", 60000, "BEVERAGE"));
        save(menuController.create("샴페인", 25000, "BEVERAGE"));
    }


    public void save(Menu menu) {
        repository.add(menu);
    }

}
