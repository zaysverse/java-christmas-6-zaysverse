package christmas.controller;

import christmas.domain.Category;
import christmas.domain.Menu;

public class MenuController {

    public Menu create(String name, int price, String category) {
        Menu menu = new Menu(name, price, Category.valueOf(category));
        return menu;
    }


}
