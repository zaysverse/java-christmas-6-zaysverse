package christmas.service;

import christmas.domain.Menu;
import christmas.repository.MenuRepository;

import java.util.NoSuchElementException;

public class MenuService {
    private MenuRepository menuRepository = MenuRepository.getInstance();

    /**
     * 메뉴 찾기
     */
    public Menu findMenu(String menuName) {
        for (Menu menu : menuRepository.findAll()) {
            if (menu.getName() == menuName) {
                return menu;
            }
        }

        throw new NoSuchElementException("그런 메뉴는 없습니다.");
    }

}
