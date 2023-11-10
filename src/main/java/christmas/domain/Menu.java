package christmas.domain;

public class Menu {

    private String name;
    private int price;
    private Category category;  // APPETIZER ,MAIN, DESSERT, BEVERAGE

    public Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static Menu createMenu(String name, int price, String category) {
        Menu menu = new Menu(name, price, Category.valueOf(category));
        return menu;
    }
}
