# 05 One of a Kind Objects

> Singleton Pattern

The Singleton Pattern is a convention for ensuring one and only one object is instantiated for a given class. It also gives us a global point of access.

## The classic Singleton Pattern implementation

```java
public class Singleton {
    // a static variable to hold our one instance of the class Singleton
    private static Singleton uniqueInstance;

    // other instance variables here

    // our constructor is declared private; only Singleton can instantiate this class
    private Singleton() {}

    public static Singleton getInstance() {
        // this method gives us a way to instantiate the class and also to return an instance of it
        if(uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

    // other useful methods here
}
```

## The Chocolate Factory

```java
public class ChocolateBoiler {
    private boolean empty;
    private boolean boiled;

    public ChocolateBoiler() {
        // this code is only started when the boiler is empty
        empty = true;
        boiled = false;
    }

    public void fill() {
        if(isEmpty()) {
            // to fill the boiler it must be empty, and, once it's full, we set the empty and boiled flags
            empty = false;
            boiled = false;
            // fill the boiler with a milk / chocolate mixture
        }
    }

    public void drain() {
        // to drain the boiler, it must be full (non empty) and also boiled. Once it is drained we set empty back to true
        if(!isEmpty() && isBoiled()) {
            // drain the boiled milk and chocolate
            empty = true;
        }
    }

    public void boil() {
        // to boil the mixture, the boiler has to be full and not already boiled. Once it's boiled we set the boiled flag to true
        if (!isEmpty() && !isBoiled()) {
            // bring the contents to a boil
            boiled = true;
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }
}
```

### The Chocolate Factory - Singleton Pattern (Exercise)

```java
public class ChocolateBoiler {
    private boolean empty;
    private boolean boiled;
    private static Singleton uniqueInstance;

    private ChocolateBoiler() {
        // this code is only started when the boiler is empty
        empty = true;
        boiled = false;
    }

    public static ChocolateBoiler getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new ChocolateBoiler();
        }
        return uniqueInstance;
    }

    public void fill() {
        if(isEmpty()) {
            // to fill the boiler it must be empty, and, once it's full, we set the empty and boiled flags
            empty = false;
            boiled = false;
            // fill the boiler with a milk / chocolate mixture
        }
    }

    // rest of ChocolateBoiler code...
}
```

## The Singleton Pattern

The uniqueInstance class variable holds our one and only instance of Singleton.

The getInstance() method is static, which means it's a class method, so you can conveniently access this method from anywhere in your code using Singleton.getInstance().
That's just as easy as accessing a global variable, but we get benefits like lazy instantiation from the Singleton.

A class implementing the Singleton Pattern is more than a Singleton; it is a general purpose class with its own set of data and methods.
