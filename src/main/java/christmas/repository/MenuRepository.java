package christmas.repository;

import christmas.domain.Menu;

import java.util.ArrayList;
import java.util.List;

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
