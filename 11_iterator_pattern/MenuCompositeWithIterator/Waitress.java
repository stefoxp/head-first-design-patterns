package MenuCompositeWithIterator;

import java.util.Iterator;

public class Waitress {
    MenuComponent allMenus;
    
    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }
    
    // print the entire menu hierarchy
    public void printMenu() {
        allMenus.print();
    }
    
    // takes the allMenu's composite and gets its iterator
    public void printVegetarianMenu() {
        Iterator iterator = allMenus.createIterator();
        System.out.println("\nVEGETARIAN MENU\n----");
        // iterate through every element of the composite
        while (iterator.hasNext()) {
            MenuComponent menuComponent = (MenuComponent)iterator.next();
            
            try {
                // call each element's isVegetarian() and 
                // if is true we call its print() method
                if (menuComponent.isVegetarian()) {
                    menuComponent.print();
                }
            // we implemented isVegetarian on the Menus to always throw an exception
            // if that happens we catch the exception, but continue with our iteraction
            } catch (UnsupportedOperationException e) {}
        }
    }
}
