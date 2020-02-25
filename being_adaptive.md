# Being Adaptive

The OO adapters take an interface and adapt it to one that a client is expecting.
The adapter as the middleman by receiving request from the client and converting them into requests that make sense on the vendor classes.

- Implementation Code [Duck adapter](07_duck_adapter)

## The Adapter Pattern explained

- the client makes a request to the adapter by calling a method on it using the target interface
- the adapter translates that request into one or more calls on the adaptee using the adaptee interface
- the client receives the results of the call and never knows there is an adapter doing the translation

Note that the Client ad Adapter are decoupled: neither knows about the other.

- Definition of the Adapter Pattern

## The Adapter Pattern - class diagram

```java
/* object adapter */

interface Target {
    request()
}

class Adapter implements Target {
    request()
}

// Adapter is composed with the Adaptee
class Adaptee {
    // all requests get delegated to the Adaptee
    specificRequest()
}

// the client sees only the Target interface
class Client {}
```

The Adapter Pattern is full of good OO design principles:

- the use of object composition to wrap the adaptee with an altered interface has the added advantage that we can use an adapter with any subclass of the adaptee.
- the pattern binds the client to an interface, not an implementation. We could use several adapters, each converting a different back-end set of classes. We could add new implementations after the fact, as long as they adhere to the Target interface.

There are two kinds of adapters:

1. **object** adapters
2. **class** adapters

To implement the class adapters you need multiple inheritance, which isn't possible in Java.

```c++
/* class adapter */

class Target {
    request()
}

class Adaptee {
    specificRequest()
}

// the Adapter now subclasses the Adaptee and the Target classes
class Adapter: Target, Adapteee {
    request()
}

// the client sees only the Target class
class Client {}
```

## Real world adapters

### Old world Enumerators

In Java, the early collections types implement a method elements(), which returns an Enumeration:

```java
// has a simple interface
interface Enumeration {
    // tell you if there are any more elements in the collection
    hasMoreElements()
    // gives you the next element in the collection
    nextElement()
}
```

### New world Iterators

When Sun released their more recent Collections classes they began using an Iterator interface that allows you to iterate through a set of items in a collection, but also adds the ability to remove items:

```java
interface Iterator {
    // tells you if you've looked at all the items in the collection
    hasNext()
    // gives you the next element in the collection
    next()
    // remove an item from the collection
    remove()
}
```

### Today

We are often faced with legacy code that exposes the Enumerator interface, yet we'd like for our new code to only use Iterators. It looks like we need to build an adapter.

- interface Iterator = Target interface
- interface Enumeration = Adaptee interface

How to map the remove() method in Iterator from the Enumeration?

```java
/* class diagram */

// we're making the Enumerations in your old code look like Iterators for your new code

// your code still gets to use Iterators, even if there's really an Enumeration underneath
interface Iterator {
    hasNext()
    next()
    remove()
}

// EnumerationIterator is the adapter
class EnumerationIterator implements Iterator {
    hasNext()
    next()
    remove()
}

// a class implementing the Enumeration interface is the adaptee
interface Enumeration {
    hasMoreElements()
    nextElement()
}
```

We know Enumeration just doesn't support remove. It's a "read only" interface. There's no way to implement a fully functioning remove() method on the adapter. The best we can do is throw a runtime exception. (luckily remove supports an UnsupportedOperationException)

```java
/* sample */

// our Adapter implements the Iterator interface...
// it has to look like an Iterator
public class EnumerationIterator implements Iterator {
    Enumeration enum;

    // we're using composition so we stash it in an instance variable
    public EnumerationIterator(Enumeration enum) {
        this.enum = enum;
    }

    public boolean hasNext() {
        // the method is delegated to the Enumeration's hasMoreElements() method
        return enum.hasMoreElements();
    }

    public Object next() {
        // is delegated to the Enumeration's nextElement() method
        return enum.nextElement();
    }

    public void remove() {
        // we can't support Iterator's remove() method
        throw new UnsupportedOperationException();
    }
}
```

## The Facade Pattern

With the Facade Pattern you can take a complex subsystem and make it easier to use by implementing a Facade class that provides one, more reasonable interface.
The Facade still leaves the subsystem accessible to be used directly. If you need the advanced functionality of the subsystem classes, they are available for your use.

Advantages:

- provides a simpler interface to a subsystem
- also allows you to decouple your client implementation from any one subsystem

- Implementation Code [Home theater facade](08_home_theater_facade)
