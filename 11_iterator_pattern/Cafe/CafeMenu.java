package Cafe;

import java.util.Hashtable;
import DinerAndPancakeHouseIterator.MenuItem;
import java.util.Iterator;

/**
 * implement the Menu interface so the Waitress can use it just like the other two Menus
 * @author stefano
 */
public class CafeMenu implements Menu {
    // the cafe is storing their menu items in a Hashtable
    // we're using Hashtable because it's a common data structure for storing values
    // you could use the newer HasMap
    Hashtable menuItems = new Hashtable();
    
    public CafeMenu() {
        addItem("Veggie Burger and Air Fries",
                "Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
                true,
                3.99);
        addItem("Soup of the day",
                "A cup of the soup of the day, with a side salad",
                false,
                3.69);
        addItem("Burrito",
                "A large burrito, with whole pinto beans, salsa, guacamole",
                true,
                4.29);
    }

    public void addItem(String name,
            String description,
            boolean vegetarian,
            double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.put(menuItem.getName(), menuItem);
    }
    
    /* we can get rid of getItems() so we don't expose the implementation of
    * menuItems to the Waitress
    
    public Hashtable getItems(){
        return menuItems;
    }
    */

    public Iterator createIterator() {
        // we're getting an Iterator only for the values of Hashtable
        return menuItems.values().iterator();
    }
    
    
}
