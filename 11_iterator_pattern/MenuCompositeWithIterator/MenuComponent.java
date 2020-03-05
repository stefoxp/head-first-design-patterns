package MenuCompositeWithIterator;

import java.util.Iterator;

public abstract class MenuComponent {
    /*
    provides default implementations for every method
    
    because some of these methods only make sense for MenuItems
    and some only for Menus, 
    the default implementation is UnsupportedOperationException
    */
    
    // we have grouped together the "composite" methods (add, remove and get)
    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }
    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }
    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    // the "operation" methods used by the MenuItems
    public String getName() {
        throw new UnsupportedOperationException();
    }
    
    public String getDescription() {
        throw new UnsupportedOperationException();
    }
    
    public double getPrice() {
        throw new UnsupportedOperationException();
    }
    
    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }
    
    // is an operation method that both Menu and MenuItems will implement
    // we provide a default operation here
    public void print() {
        throw new UnsupportedOperationException();
    }
    
    // each Menu and MenuItem will need to implement this method
    public Iterator createIterator() {
        throw new UnsupportedOperationException();        
    }
}
