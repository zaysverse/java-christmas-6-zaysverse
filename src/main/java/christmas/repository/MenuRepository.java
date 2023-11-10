package christmas.repository;

import christmas.controller.MenuController;
import christmas.domain.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static christmas.domain.Menu.createMenu;

public class MenuRepository {

    private static MenuRepository menuRepository;

    List<Menu> repository;

    private MenuRepository() {
        this.repository = new ArrayList<>();
        init();
    }

    public static MenuRepository getInstance() {
        if (menuRepository == null) {
            menuRepository = new MenuRepository();
        }
        return menuRepository;
    }

    private void init() {
        save(createMenu("양송이수프", 6000, "APPETIZER"));
        save(createMenu("타파스", 5500, "APPETIZER"));
        save(createMenu("시저샐러드", 8000, "APPETIZER"));

        save(createMenu("티본스테이크", 55000, "MAIN"));
        save(createMenu("바비큐립", 54000, "MAIN"));
        save(createMenu("해산물파스타", 35000, "MAIN"));
        save(createMenu("크리스마스파스타", 25000, "MAIN"));

        save(createMenu("초코케이크", 15000, "DESSERT"));
        save(createMenu("아이스크림", 5000, "DESSERT"));

        save(createMenu("제로콜라", 3000, "BEVERAGE"));
        save(createMenu("레드와인", 60000, "BEVERAGE"));
        save(createMenu("샴페인", 25000, "BEVERAGE"));
    }


    public void save(Menu menu) {
        repository.add(menu);
    }

    public List<Menu> findAll() {
        return repository;
    }

}
