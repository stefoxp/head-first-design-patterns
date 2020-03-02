# Well-Managed Collections

There are lots of ways to stuff objects into a collection. Put them in an Array, a List, a Hashtable, take your pick. Each has its own advantages and tradeoffs.
How you can allow your clients to iterate through your objects without ever getting a peek at how store your objects? How to create some super collections of objects that can leap over some impressive data structures in a single bound?

## Objectville Diner and Pancake House Merge

The Diner menu has lots of lunch items, while the Pancake House consists of breakfast items. Every menu item has a name, a description, and a price.

```java
public class MenuItem {
    String name;
    String description;
    boolean vegetarian;
    double price;

    // a MenuItem consists of a name, a description, a flag to indicate if the item is vegetarian, and a price
    public MenuItem(String name,
                    String description,
                    boolean vegetarian,
                    double price)
    {
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
```

Both have lots of time and code invested in the way they store their menu items in a menu, and lots of other code that depends on it.

```java
// on the Pancake House they use an ArrayList that makes the menu easily to expand
public class PancakeHouseMenu {
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

    public ArrayList getMenuItems() {
        return menuItems;
    }

    // a bunch of other menu code that depends on the ArrayList implementation
}

// on the Diner they use an Array so they control the maximum size of their menu and get their MenuItems without having to use a cast

public class DinerMenu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

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

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    // bunch of code that depends on the implementation of his menu being an Array
}
```

Having two different menu representations complicates things.

We're going to have the job of implementing a common Waitress that is going to be hard to maintain and extend.
It would really be nice if we could find a way to allow them to implement the **same interface** for their menus.

We can encapsulate the iteration caused by different collections of objects being returned from the menus:

```java
// 1) to iterate through the breakfast items we use the size() and get() methods on the ArrayList

for (int i = 0; i < breakfastItems.size(); i++) {
    MenuItem menuItem = (MenuItem)breakfastItems.get(i);
}

// 2) and to iterate through the lunch items we use the Array length field and the array subscript notation on the MenuItem Array

for (int i = 0; i < lunchItems.length; i++) {
    MenuItem menuItem = lunchItems[i];
}

// 3) what if we create an object, let's call it an Iterator that encapsulates the way we iterate through a collection of objects?

// try on the ArrayList
Iterator iterator = breakfastMenu.createIterator();

while (iterator.hasNext()) {
    MenuItem menuItem = (MenuItem)iterator.next();
}

// try on the Array
Iterator iterator = lunchMenu.createIterator();

while (iterator.hasNext()) {
    // this code is exactly the same
    MenuItem menuItem = (MenuItem)iterator.next();
}
```

## The Iterator Pattern

This pattern relies on an interface called Iterator

```java
// the interface class diagram
interface Iterator {
    // tells us if there are more elements in the aggregate to iterate through
    hasNext()
    // returns the next object in the aggregate
    next()
}
```

We can implement Iterators for any kind of collection of objects: arrays, lists, hashtables,...

- Implementation Code [Diner and Pancake House Iterator](11_diner_and_pancake_iterator)

Once we gave a PancakeHouseMenuIterator and a DinerMenuIterator, all they had to do was add a getIterator() method and they were finished.
The Waitress will be much easier to maintain and extend down the road.

```java
// the current design

// we're now using a common Iterator interface and we've implemented two concrete classes
interface Iterator {
    hasNext()
    next()
}

// the Iterator allows the Waitress to be decoupled from the actual implementation of the concrete classes
class Waitress {
    printMenu()
}

// these two menus implement the same exact set of methods, but they aren't implementing the same Interface. We're going to fix this and free the Waitress from any dependencies on concrete Menus
class PancakeHouseMenu {
    menuItems
    createIterator()
}

class DinerMenu {
    menuItems
    createIterator()
}

// implement the new createIterator() method; they are responsible for creating the iterator for their respective menu items implementations
class PancakeHouseMenuIterator implements Iterator {
    hasNext()
    next()
}

class DinerMenuIterator implements Iterator {
    hasNext()
    next()
}
```

- the iterator give us a way to step through the elements of an aggregate without forcing the aggregate to clutter its own interface with a bunch of methods to support traversal of its elements
- it also allows the implementation of the iterator to live outside of the aggregate (we've encapsulated the interaction)

