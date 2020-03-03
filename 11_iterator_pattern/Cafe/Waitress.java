package Cafe;

import DinerAndPancakeHouseIterator.MenuItem;
import java.util.Iterator;

public class Waitress {
    Menu pancakeHouseMenu;
    Menu dinerMenu;
    // the menu is passed into the Waitress in the constructor with the other
    // and we stash it in an instance variable
    Menu cafeMenu;

    public Waitress(Menu pancakeHouseMenu, Menu dinerMenu, Menu cafeMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
        this.cafeMenu = cafeMenu;
    }

    public void printMenu() {
        // creates one iterator for each menu
        Iterator pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator dinerIterator = dinerMenu.createIterator();
        Iterator cafeIterator = cafeMenu.createIterator();

        System.out.println("MENU\n----\nBREAKFAST");
        // then calls the overloaded printMenu() with each iterator
        printMenu(pancakeIterator);
        
        System.out.println("\nLUNCH");
        printMenu(dinerIterator);
        
        System.out.println("\nDINNER");
        printMenu(cafeIterator);
    }

    /**
     * Nothing changes here
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