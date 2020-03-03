package DinerAndPancakeHouseIterator;

import java.util.ArrayList;

public class PancakeMenuIterator implements Iterator {
    ArrayList items;
    int position = 0;

    public PancakeMenuIterator(ArrayList items) {
        this.items = items;
    }

    public boolean hasNext() {
        // use the size() method on the ArrayList
        if (position >= items.size()) {
            return false;
        } else {
            // true if there are more to iterate through
            return true;
        }
    }

    public Object next() {
        // use the size() method on the ArrayList
        MenuItem menuItem = (MenuItem)items.get(position);
        position = position + 1;
        return menuItem;
    }
}
