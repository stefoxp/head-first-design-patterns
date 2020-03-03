package Cafe;

import DinerAndPancakeHouseIterator.MenuItem;
import java.util.Iterator;

public class DinerMenu implements Menu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    // constructor here
    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("Vegetarian BLT",
                "(Fakin') Bacon with lettuce & tomato on whole wheat",
                true,
                2.99);

        addItem("BLT",
                "Bacon with lettuce & tomato on whole wheat",
                false,
                2.99);

        addItem("Soup of the day",
                "Soup of the day, with a side of potato salad",
                false,
                3.29);

        addItem("Hotdog",
                "A hot dog, with saurkraut, relish, onions, topped with cheese",
                false,
                3.05);
    }

    // addItem here
    public void addItem(String name,
                        String description,
                        boolean vegetarian,
                        double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        // checks to make sure we haven't hit the menu size limit
        if (numberOfItems >= MAX_ITEMS) {
            System.err.println("Sorry, menu is full! Can't add item to menu");
        } else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems = numberOfItems + 1;
        }
    }

    /**
     * we're not going to need the getMenuItems() method anymore
     * we don't want it because it exposes our internal implementation
     * @return
     */
    /* public MenuItem[] getMenuItems() {
        return menuItems;
    } */

    /**
     * creates a DinerMenuIterator from the menuItems array and returns it to the client
     * @return
     */
    public Iterator createIterator() {
        /*
        * we're returning the Iterator interface.
        * The client doesn't need to know how the menuItems are maintained in the DinerMenu,
        * nor does it need to know how the DinerMenuIterator is implemented.
        * it just needs to use the iterators to step through the items in the menu
        *  */
        return new DinerMenuIterator(menuItems);
    }

    // other menu methods here
}