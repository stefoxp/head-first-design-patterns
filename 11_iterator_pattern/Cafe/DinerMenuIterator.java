package Cafe;

import DinerAndPancakeHouseIterator.MenuItem;
import java.util.Iterator;

/**
 * concrete Iterator that works for the Diner menu
 */
public class DinerMenuIterator implements Iterator {
    MenuItem[] items;
    // position maintains the current position of the iteration over the array
    int position = 0;

    /**
     * takes the array of menu items we are going to iterate over the array
     * @param items
     */
    public DinerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    public boolean hasNext() {
        // we need to check not only if we are at the end of the array,
        // but also it the next item is null, which indicates there are no more items
        if (position >= items.length || items[position] == null) {
            return false;
        } else {
            // true if there are more to iterate through
            return true;
        }
    }

    /**
     * returns the next item in the array and increments the position
     * @return
     */
    public Object next() {
        MenuItem menuItem = items[position];
        position = position + 1;
        return menuItem;
    }
    
    // we do need to implement remove()
    public void remove() {
        if (position <= 0) {
            throw new IllegalStateException ("You can't remove an item you've done at least one next()");
        }
        // we just shift all the elements up one when remove() is called
        if (items[position-1] != null) {
            for (int i = position-1; i < (items.length-1); i++) {
                items[i] = items[i+1];
            }
            items[items.length-1] = null;
        }
    }
}
