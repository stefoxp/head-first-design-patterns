# Encapsulating Algorithms

## The coffee and tea classes

```java
public class Coffee {
    void prepareRecipe() {
        // each step is implemented as a separate method
        boilWater();
        brewCoffeeGrinds();
        pourInCup();
        addSugarAndMilk();
    }

    public void boilWater() {
        System.out.println("Boiling water");
    }

    public void brewCoffeeGrinds() {
        System.out.println("Dripping Coffee through filter");
    }

    public void pourInCup() {
        System.out.printl("Pouring into cup");
    }

    public void addSugarAndMilk() {
        System.out.println("Adding Sugar and Milk");
    }
}

public class Tea {
    void prepareRecipe() {
        // this look very similar to the one we just implemented in Coffee
        boilWater();
        steepTeaBag();
        pourInCup();
        addLemon();
    }

    // this method is exactly the same as it is in Coffee = code duplication
    public void boilWater() {
        System.out.println("Boiling water");
    }

    // this method is specialized to Tea
    public void steepTeaBag() {
        System.out.println("Steeping the tea";
    }

    // this method is specialized to Tea
    public void addLemon() {
        System.out.println("Adding Lemon");
    }

    // this method is exactly the same as it is in Coffee = code duplication
    public void pourInCup() {
        System.out.printl("Pouring into cup");
    }
}
```

We've got code duplication, we need to clean up the design.
It seems like here we should abstract the commonality into a base class.

### Abstraction - first cut

```java
/* class diagram */
abstract class CaffeineBeverage {
    // prepareRecipe() differs in each subclass so it is defined as abstract
    abstract prepareRecipe();
    // this methods are shared by both subclasses so they are defined in the superclass
    boilWater();
    pourInCup();
}

class Coffee extends CaffeineBeverage {
    // each subclass override the prepareRecipe() method and implements its own recipe
    prepareRecipe();

    // the method specific stay in the subclasses
    breCoffeeGrinds();
    addSugarAndMilk();
}

class Tea extends CaffeineBeverage {
    // each subclass override the prepareRecipe() method and implements its own recipe
    prepareRecipe();

    // the method specific stay in the subclasses
    steepTeaBag();
    addLemon();
}
```

### Abstraction - second step

Notice that both recipes follow the same algorithm:

1. boil some water
2. use the hot water to extract the coffee or tea
3. pour the resulting beverage into a cup
4. add the appropriate condiments to the beverage

abstracting prepareRecipe() from each subclass:

- the first problem is that Coffee uses brewCoffeeGrinds() and addSugarAndMilk() while Tea uses steepTeaBag() and addLemon() methods.

Steeping and brewing are analogous, so let's make a new method name brew(), and we'll use the same name whether we're brewing coffee or steeping tea.
Likewise, adding sugar and milk is the same as adding a lemon. Make up a new method name addCondiments() to handle this.

Our new prepareRecipe() method will look like this:

```java
public abstract class CaffeineBeverage {
    // the same method will be used to make both Tea and Coffee
    // is declared final because we don't want our subclasses to be able to override it
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // because Coffee and Tea handle these methods in different ways, they're going to have to be declared as abstract
    abstract void brew();
    abstract void addCondiments();

    // we moved these into the CaffeineBeverage class
    void boilWater() {
        System.out.println("Boiling water");
    }

    void pourInCup() {
        System.out.println("Pouring into cup");
    }
}

public class Tea extends CaffeineBeverage {
    public void brew() {
        System.out.println("Steeping the tea");
    }

    public void addCondiments() {
        System.out.println("Adding Lemon");
    }
}

public class Coffee extends CaffeineBeverage {
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}
```

## The Template Method Pattern

We've implemented the Template Method Pattern.

prepareRecipe() is our template method because:

1. it is a method, after all
2. it serves as a template for an algorithm.
In this case, an algorithm for making caffeinated beverages.

The Template Method defines the steps of an algorithm and allows subclasses to provide the implementation for one or more steps.

- Definition of the Template Method Pattern

A template it's a method that defines an algorithm as a set of steps. One or more of these steps is defined to be abstract and implemented by a subclass.

### The Template Method Pattern - class diagram

```java
abstract class AbstractClass {
    final templateMethod()
    // abstract versions of the opearations used in the template method
    abstract primitiveOperation1()
    abstract primitiveOperation2()
}

// there may be many ConcreteClass, each implementing the full set of operations required by the template method
class ConcreteClass extends AbstractClass {
    primitiveOperation1()
    primitiveOperation2()
}
```