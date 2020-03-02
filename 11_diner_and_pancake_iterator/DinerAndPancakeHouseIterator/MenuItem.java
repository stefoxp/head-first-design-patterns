public class MenuItem {
    String name;
    String description;
    boolean vegetarian;
    double price;

    // a MenuItem consists of a name, a description, a flag to indicate if the item is vegetarian, and a price
    public MenuItem(String name,
                    String description,
                    boolean vegetarian,
                    double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    // these getter methods let you access the fields of the menu item

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }
}
