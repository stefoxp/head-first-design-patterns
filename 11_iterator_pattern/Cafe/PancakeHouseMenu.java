package Cafe;

import DinerAndPancakeHouseIterator.MenuItem;
import java.util.ArrayList;
import java.util.Iterator;

public class PancakeHouseMenu implements Menu {
    ArrayList menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList();

        // each menu item is added to the ArrayList here, in the constructor

        addItem("K&B' Pancake Breakfast",
                "Pancake with scrambled eggs, and toast",
                true,
                2.99);

        addItem("Regular Pancake Breakfast",
                "Pancake with fried eggs, sausage",
                false,
                2.99);

        addItem("Blueberry Pancakes",
                "Pancakes made with fresh blueberries",
                true,
                3.49);

        addItem("Waffles",
                "Waffles, with your choice of blueberries or strawberries",
                true,
                3.59);
    }

    public void addItem(String name,
                        String description,
                        boolean vegetarian,
                        double price) {
        // to add a menu item, creates a new MenuItem object, passing in each argument, and then adds it to the ArrayList
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    public Iterator createIterator() {
        // instead of creating our own iterator now
        // we just call the iterator() method on the menuItems ArrayList
        return menuItems.iterator();
    }
}
