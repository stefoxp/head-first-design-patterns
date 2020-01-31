# 04 Baking with OO Goodness

>Factory Pattern

## Pizza store sample

### Start sample

```java
Pizza orderPizza() {
    Pizza pizza = new Pizza();

    pizza.prepare();
    pizza.bake();
    pizza.cut();
    pizza.box();
    return pizza;
}

// if you need more than one type of pizza
Pizza orderPizza(String type) {
    Pizza pizza;

    // we instantiate the correct concrete class and assign it to the pizza instance variable
    if (type.equals("cheese")) {
        pizza = new CheesePizza();
    } else if (type.equals("greek")) {
        pizza = new GreekPizza();
    } else if (type.equals("pepperoni")) {
        pizza = PepperoniPizza();
    }

    // each Pizza subtype knows how to prepare itself
    pizza.prepare();
    pizza.bake();
    pizza.cut();
    pizza.box();
    return pizza;
}

// N.B. but the pressure is on to add more pizza types
```

This code is NOT closed for modification.

The if ... else if code varies. As the pizza selection changes over time, you'll have to modify this code over and over.

The pizza.prepare() ... pizza.box() code is what we expect to stay the same.

### Encapsulating object creation

```java
// First we pull this code out of the orderPizza method
if (type.equals("cheese")) {
    pizza = new CheesePizza();
} else if (type.equals("greek")) {
    pizza = new GreekPizza();
} else if (type.equals("pepperoni")) {
    pizza = PepperoniPizza();
}

// Then we place that code in an object that is only going to worry about how to create pizzas. (Es. SimplePizzaFactory class)
```

Factories handle the details of object creation.

### A simple pizza factory

```java
// this class has one job in life: creating pizzas for its clients
public class SimplePizzaFactory {
    // this is the method all clients will use to instantiate new objects
    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("greek")) {
            pizza = new GreekPizza();
        } else if (type.equals("pepperoni")) {
            pizza = PepperoniPizza();
        }
        return pizza;
    }
}

public class PizzaStore {
    SimplePizzaFactory factory;

    // gets the factory passed to it in the constructor
    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza;
        // uses the factory to create its pizzas by simply passing on the type of the order
        pizza = factory.createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    // other methods
}
```

The Simple Factory isn't actually a Design Pattern it's more of a programming idiom.

#### Simple pizza class diagram

```java
// the client of the factory
class PizzaStore {
    orderPizza()
}

// the factory; it should be the only part of our application that refers to concrete Pizza classes
class SimplePizzaFactory {
    createPizza()
}

// the product of the factory: pizza
abstract class Pizza {
    prepare()
    bake()
    cut()
    box()
}

// concrete products
class CheesePizza extends Pizza {}
class PepperoniPizza extends Pizza {}
```

### A Framework for the pizza store

```java
public abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza;

        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    // now we've moved our factory object to this method
    protected abstract Pizza createPizza(String type);

    // all the responsability for instantiating Pizzas has been moved into a method that acts as a factory
}

// subclasses sample for franchising

// extends PizzaStore, so it inherits the orderPizza() method
class NYStylePizzaStore extends PizzaStore {
    Pizza createPizza(type) {
        // for each type of Pizza we create the NY style
        if (type.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else if (type.equals("pepperoni")) {
            return NYStylePepperoniPizza();
        } else return null;
    }
}

class ChicagoStylePizzaStore extends PizzaStore {
    Pizza createPizza(type) {
        if (type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        } else if (type.equals("pepperoni")) {
            return ChicagoStylePepperoniPizza();
        } else return null;
    }
}
```

A factory method handles object creation and encapsulates it in a subclass. This **decouples** the client in the superclass from the object creation code in the subclass:

```java
abstract Product factoryMethod(String type)
```

A factory method:

- is **abstract** so the subclasses are counted on to handle object creation;
- returns a **Product** that is typically used within methods defined in the superclass;
- **isolates the client** (the code in the superclass) from knowing what kind of concrete Product is actually created;
- **may be parametrized (or not)** to select among several variations of a product;

- Implementation Code [Pizza Store Simple 05](05_pizza_store)
