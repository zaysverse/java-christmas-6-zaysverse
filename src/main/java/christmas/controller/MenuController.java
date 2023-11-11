package christmas.controller;

import christmas.domain.Category;
import christmas.domain.Menu;
import christmas.service.MenuService;
import christmas.view.InputView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MenuController {

    public static final String FILE_NAME = "E:\\java-christmas-6-zaysverse\\src\\main\\java\\christmas\\controller\\menu.txt";

    private static MenuController menuController;

    MenuService menuService = MenuService.getInstance();

    private MenuController() {
        init();
    }

    public static MenuController getInstance() {
        if (menuController == null) {
            menuController = new MenuController();
        }
        return menuController;
    }

    public Menu create(String name, int price, String category) {
        Menu menu = Menu.createMenu(name, price, Category.valueOf(category));
        return menu;
    }

    private void init() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                menuService.save(create(split[0], Integer.parseInt(split[1]), split[2]));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
