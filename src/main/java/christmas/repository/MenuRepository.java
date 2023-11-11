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
    }

    public static MenuRepository getInstance() {
        if (menuRepository == null) {
            menuRepository = new MenuRepository();
        }
        return menuRepository;
    }

    public void save(Menu menu) {
        repository.add(menu);
    }

    public List<Menu> findAll() {
        return repository;
    }

}
