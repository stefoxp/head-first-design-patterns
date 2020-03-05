package MenuCompositeWithIterator;

import java.util.Iterator;

/**
 * a MenuItem has nothing to iterate over
 * how do we handle the implementation of its createIterator() method
 * 1. return null
 * 2. return an iterator that always returns false when hasNext() is called
 * @author stefano
 */
public class NullIterator implements Iterator {

    @Override
    public boolean hasNext() {
     return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
}
