package DinerAndPancakeHouseIterator;

import java.awt.*;

public class Waitress {
    PancakeHouseMenu pancakeHouseMenu;
    DinerMenu dinerMenu;

    /**
     * takes the two menus
     * @param pancakeHouseMenu
     * @param dinerMenu
     */
    public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public void printMenu() {
        // creates two iterators, one for each menu
        Iterator pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator dinerIterator = dinerMenu.createIterator();

        System.out.println("MENU\n----\nBREAKFAST");

        // then calls the overloaded printMenu() with each iterator
        printMenu(pancakeIterator);
        System.out.println("\nLUNCH");
        printMenu(dinerIterator);
    }

    /**
     * uses the Iterator to step through the menu items and print them
     * @param iterator
     */
    private void printMenu(Iterator iterator) {
        // test if there are any more items
        while (iterator.hasNext()) {
            // get the next item
            MenuItem menuItem = (MenuItem)iterator.next();
            // use the item to get name, price, and description and print them
            System.out.print(menuItem.getName() + ", ");
            System.out.print(menuItem.getPrice() + " -- ");
            System.out.println(menuItem.getDescription());
        }
    }

    // other methods here
}