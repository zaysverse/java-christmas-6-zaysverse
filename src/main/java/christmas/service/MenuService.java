package christmas.service;

import christmas.controller.MenuController;
import christmas.domain.Menu;
import christmas.repository.MenuRepository;

import java.util.NoSuchElementException;

import static christmas.ErrorMessage.INVALID_ORDER;

public class MenuService {

    private static MenuService menuService;

    private MenuRepository menuRepository = MenuRepository.getInstance();

    private MenuService() {
    }

    public static MenuService getInstance() {
        if (menuService == null) {
            menuService = new MenuService();
        }
        return menuService;
    }

    /**
     * 메뉴 찾기
     */
    public Menu findMenu(String menuName) {
        for (Menu menu : menuRepository.findAll()) {
            if (menu.getName().equals(menuName)) {
                return menu;
            }
        }

        throw new NoSuchElementException(INVALID_ORDER.getMessage());
    }

    public void save(Menu menu) {
        menuRepository.save(menu);
    }

}
