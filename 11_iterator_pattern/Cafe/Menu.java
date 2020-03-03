package Cafe;

import java.util.Iterator;

public interface Menu {
    // lets clients get an iterator for the items in the menu
    public Iterator createIterator();
}