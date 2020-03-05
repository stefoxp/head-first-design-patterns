package MenuCompositeWithIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class Menu extends MenuComponent {
    // can have any number of children
    // we'll use an internal ArrayList to hold these
    ArrayList menuComponents = new ArrayList();
    String name;
    String description;
    
    // we're going to give each Menu a name and a description
    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }
    
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }
    
    public MenuComponent getChild(int i) {
        return (MenuComponent)menuComponents.get(i);
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    // we aren't overriding getPrice() or isVegetarian() because 
    // these methods don't make sense for a Menu
    
    public void print() {
        System.out.print("\n" + getName());
        System.out.println(", " + getDescription());
        System.out.println("---------------------");
        
        /* because menu is a composite and contains both 
        * Menu Items and other Menus, 
        its print() methods should print everything it contains
        */
        Iterator iterator = menuComponents.iterator();
        // we use an Iterator to iterate through all the Menu's components
        while (iterator.hasNext()) {
            MenuComponent menuComponent = (MenuComponent)iterator.next();
            menuComponent.print();
        }
    }
    
    public Iterator createIterator() {
        // it knows how to iterate over any composite
        return new CompositeIterator(menuComponents.iterator());
    }
   
}
