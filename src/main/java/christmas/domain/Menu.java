package christmas.domain;

public class Menu {

    private String name;
    private int price;
    private Category category;  // APPETIZER ,MAIN, DESSERT, BEVERAGE

    protected Menu(String name, int price, Category category) {
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

    public Category getCategory() {
        return category;
    }

    public static Menu createMenu(String name, int price, Category category) {
        Menu menu = new Menu(name, price, category);
        return menu;
    }
}
