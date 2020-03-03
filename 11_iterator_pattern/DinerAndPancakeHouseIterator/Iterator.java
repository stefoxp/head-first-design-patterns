package DinerAndPancakeHouseIterator;

public interface Iterator {
    // returns a boolean indicating whether or not there are more elements to iterate over
    boolean hasNext();
    // returns the next element
    Object next();
}