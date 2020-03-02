import java.awt.*;

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
}
