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

### The Factory Method Pattern

The Factory Method Pattern encapsulates object creation by letting subclasses decide what objects to create.

#### The Creator classes - class diagram

```java
// The Creator classes ***

abstract class PizzaStore {
    // abstract creator class
    // the creator never really knows wich concrete product was produced

    abstract createPizza()
    orderPizza()
}

class NYPizzaStore extends PizzaStore {
    // concrete creator class

    createPizza()
}

class ChicagoPizzaStore extends PizzaStore {
    // concrete creator class

    createPizza()
}
// ***

// The Product classes ***
abstract class Pizza {}

// the concrete products
class NYStyleCheesePizza extends Pizza {}
class NYStylePepperoniPizza extends Pizza {}
class NYStyleClamPizza extends Pizza {}
class NYStyleVeggiePizza extends Pizza {}

class ChicagoStyleCheesePizza extends Pizza {}
class ChicagoStylePepperoniPizza extends Pizza {}
class ChicagoStyleClamPizza extends Pizza {}
class ChicagoStyleVeggiePizza extends Pizza {}

// ***
```

Another way to look at this pattern as a framework is the way it encapsulates product knowledge into each creator.
The factory method is the key to encapsulating this knowledge.

#### The Factory Method Pattern - class diagram

```java
// the Creator is a class that contains the implementation for all of the methods to manipulate products, except for the factory method
abstract class Creator {
    // the abstract factoryMethod is what all Creator subclasses must implement
    abstract factoryMethod()
    anOperation()
}

class ConcreteCreator extends Creator {
    // the ConcreteCreator implements the factoryMethod, which is the method that actually produces products
    factoryMethod()
}

abstract class Product {}

// the ConcreteCreator is responsible for creating one or more concrete products. It is the only class that has the knowledge of how to create these products
class ConcreteProduct extends Product {}
```

Questions:

- This pattern is useful if you've only got concrete creator because you are **decoupling the implementation of the product from its use**.
- The factory method and the Creator class isn't always abstract. You can define a default factory method to produce some concrete product.

### Guidelines help to respect Dependency Inversion Principle

- No variable should hold a reference to a concrete class. Use a factory to get around that.
- No class should derive from a concrete class. Derive from an abstraction (like an interface or an abstract class).
No method should override an implemented method of any of its base classes.

Every single Java program ever written violates these guidelines. You'll know when you are violating the principle and you'll have a good reason for doing so.

### Ensuring consistency in your ingredients

to build a factory that produces them and ships them to your franchises.
Problem: we've got the same product families but different implementations based on region.

```java
public interface PizzaIngredientFactory {
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggies[] createVeggies();
    public Pepperoni createPepperoni();
    public Clams createClam();
}

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    // for each ingredient in the ingredient family, we create the New York version
    public Dough createDough() {
        return new ThinCrustDough();
    }
    public Sauce createSauce() {
        return new MarinaraSauce();
    }
    public Cheese createCheese() {
        return new ReggianoCheese();
    }
    public Veggies[] createVeggies() {
        Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
        return veggies;
    }
    // this is shared between NY and Chicago
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }
    public Clams createClam() {
        return new FreshClams();
    }
}

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {...}

// reworking the pizzas
public abstract class Pizza {
    // each pizza holds a set of ingredients that are used in its preparation
    String name;
    Dough dough;
    Sauce sauce;
    Veggies veggies[];
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clams;

    abstract void prepare();

    // our other methods remain the same, with the exception of the prepare method
    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }
    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }
    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }
    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
    public String toString() {
        // code to print pizza here
    }
}

public class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    // to make a pizza now, we need a factory to provide the ingredients
    // so each Pizza class gets a factory passed into its constructor
    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    void prepare() {
        System.out.println("Preparing " + name);
        // here's where the magic happens
        // each time it needs an ingredient, it asks the factory to produce it
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
    }
}

// the client of the AbstractFactory
public class NYPizzaStore extends PizzaStore {
    protected Pizza createPizza(String item) {
        Pizza pizza = null;
        // the NY Store is composed with a NY pizza ingredient factory.
        // this will be used to produce the ingredients for all NY style pizzas
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if(item.equals("cheese")) {
            // we now pass each pizza the factory that should be used to produce its ingredients
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        } else if (item.equals("veggie")) {
            pizza = new VeggiePizza(ingredientFactory);
            pizza.setName("New York Style Veggie Pizza");
        } else if (item.equals("clam")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("New York Style Clam Pizza");
        } else if (item.equals("pepperoni")) {
            pizza = new PepperoniPizza(ingredientFactory);
            pizza.setName("New York Style Pepperoni Pizza");
        }
        return pizza;
    }
}
```

- an Abstract Factory provides an interface for a family of products;
- from the abstract factory, we derive one or more concrete factories that produce the same products, but with different implementations;
- we then write our code so that it uses the factory to create products. By passing in a variety of factories, we get a variety of implementations of those products. But our client code stays the same.

### The Abstract Factory Pattern

The client is decoupled from any of the specifics of the concrete products.

#### The Abstract Factory Pattern - class diagram

```java
// the AbstractFactory defines the interface that all Concrete factories must implement, which consists of a set of methods for producing products
interface AbstractFactory {
    CreateProductA()
    CreateProductB()
}

// the concrete factories implement the different product families. To create a product, the client uses one of these factories, so it never has to instantiate a product object
class ConcreteFactory1 implements AbstractFactory {
    CreateProductA()
    CreateProductB()
}
class ConcreteFactory2 implements AbstractFactory {
    CreateProductA()
    CreateProductB()
}

// this is the product family. Each concrete factory can produce an entire set of products
interface AbstractProductA {}

class ProductA1 implements AbstractProductA {}
class ProductA2 implements AbstractProductA {}

interface AbstractProductB {}

class ProductB1 implements AbstractProductB {}
class ProductB2 implements AbstractProductB {}

// the Client is written against the abstract factory and then composed at runtime with an actual factory
class Client {}
```

Often the methods of an Abstract Factory are implemented as factory methods.
The job of an Abstract Factory is to define an interface for creating a set of products.
Each method in that interface is responsible for creating a concrete product. So, factory methods are a natural way to implement your product methods in your abstract factories.

### The Factory Method and Abstract Factory compared

The Factory Method relies on **inheritance**: object creation is delegated to subclasses which implement the factory method to create objects.

The Abstract Factory relies on **object composition**: object creation is implemented in methods exposed in the factory interface.

All factory patterns promote loose coupling by reducing the dependency of your application on concrete class.

The intent of Factory Method is to allow a class to defer instantiation to its subclasses.

The intent of Abstract Factory is to create families of related objects without having to depend on their concrete classes.

#### Factory Method PizzaStore class diagram

```java
// is implemented as a Factory Method because we want to be able to create a product that varies by region: each region gets its own concrete factory that knows how to make pizzas which are appropriate for the area
abstract class PizzaStore {
    createPizza()
}

class NYPizzaStore extends PizzaStore {
    // the Factory Method
    createPizza()
}
class ChicagoPizzaStore extends PizzaStore {
    // the Factory Method
    createPizza()
}

// this is the product of the PizzaStore
// clients only rely on this abstract type
abstract class Pizza {}

// subclasses are instantiated by the Factory Methods
// the createPizza() method is parameterized by pizza type, so we can return many types of pizza products
class NYStyleCheesePizza extends Pizza {}
class NYStylePepperoniPizza extends Pizza {}

class ChicagoStyleCheesePizza extends Pizza {}
class ChicagoStylePepperoniPizza extends Pizza {}
```

#### Abstract Factory PizzaStore class diagram

```java
// is implemented as an Abstract Factory because we need to create families of products (the ingredients)
interface PizzaIngredientFactory {
    createDough();
    createSauce();
    createCheese();
    createVeggies();
    createPepperoni();
    createClam();
}

// each concrete subclass creates a family of products
class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    // methods to create products in an Abstract Factory are often implemented with a Factory Method
    createDough();
    createSauce();
    createCheese();
    createVeggies();
    createPepperoni();
    createClam();
}
class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    createDough();
    createSauce();
    createCheese();
    createVeggies();
    createPepperoni();
    createClam();
}

// the subclass decides the type of dough
interface Dough {}

// the product subclasses create parallel sets of product families
class ThickCrustDough implements Dough {}
class ThinCrustDough implements Dough {}

// the subclass decides the type of clams
interface Clams {}

class FrozenClams implements Clams {}
class FreshClams implements Clams {}
```
