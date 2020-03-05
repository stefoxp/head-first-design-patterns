package MenuCompositeWithIterator;

import java.util.Iterator;
import java.util.Stack;

/**
 * iterating over the MenuItems in the component
 * and making sure all the child Menus are included
 * @author stefano
 */
public class CompositeIterator implements Iterator {
    Stack stack = new Stack();

    public CompositeIterator(Iterator iterator) {
        // throw the iterator of the top level composite in a stack data structure
        stack.push(iterator);
    }
    
    public Object next() {
        if (hasNext()) {
            Iterator iterator = (Iterator)stack.peek();
            // if there is a next element, we get the current iterator off the stack
            // and get its next element
            MenuComponent component = (MenuComponent)iterator.next();
            if (component instanceof Menu) {
                // if that element is a menu, we have another composite
                // so we throw it on the stack
                stack.push(component.createIterator());
            }
            return component;
        } else {
            return null;
        }
    }

    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        } else {
            // we get the iterator off the top of the stack 
            // and see if it has a next element
            Iterator iterator =  (Iterator)stack.peek();
            // recursive call
            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    // we're not supporting remove
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
}
