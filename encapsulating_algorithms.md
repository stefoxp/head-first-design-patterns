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
    final void templateMethod() {
        pimitiveOperation1();
        primitiveOperation2();
        concreteOperation();
        hook();
    }
    // abstract versions of the opearations used in the template method
    abstract primitiveOperation1() {}
    abstract primitiveOperation2() {}

    final void concreteOperation() {
        // a concrete operation is defined in the abstract class
        // this one is declared final so that subclasses can't override it
        // it may be used in the template method directly, or used by subclasses
    }

    void hook() {
        // we can also have concrete method that do nothing by default = hook
        // subclasses are free to override these but don't have to
    }

}

// there may be many ConcreteClass, each implementing the full set of operations required by the template method
class ConcreteClass extends AbstractClass {
    primitiveOperation1();
    primitiveOperation2();
}
```

### Hook method

A hook is a method that is declared in the abstract class, but only given an empty or default implementation. This gives subclasses the ability to hook into the algorithm at various points or ignore the hook.

- Implementation Code [Caffeine Beverage With Hook](09_caffeine_beverage_with_hook)

- use abstract methods when your subclass must provide an implementation of the method or step in algorithm
- use hooks when that part of the algorithm is optional

## The Hollywood Principle

- Definition of the Hollywood Principle

Gives us a way to prevent dependency rot: happens when you have high-level components depending on low-level components depending on high-level components and so on.

With this principle we allow low-level components to hook themselves into a system, but the high-level components determine when they are needed, and how (the high-level components give the low-level components a don't call us, we'll call you treatment).

The connection between the Hollywood Principle and the Template Method Pattern is probably somewhat apparent:
CaffeineBeverage is our high-level component. It has control over the algorithm for the recipe, and calls on the subclasses only when they're needed for an implementation of a method.
Clients of beverages will depend on the CaffeineBeverage which reduces dependencies in the overall system.

## Template Method Pattern in the Wild

### Sorting

The designers of the Java Arrays class have provided us with a handy template method for sorting.

```java
// we actually have two methods here and they act together to provide the sort functionality

// the first method is just a helper method that creates a copy of the array and passes it along as the destination array to the mergeSort()
public static void sort(Object[] a) {
    Object aux[] = (Object[])a.clone();
    mergeSort(aux, a, 0, a.lenght, 0);
}

// the mergeSort() contains the sort algorithm, and relies on an implementation of the compareTo() to complete the algorithm
private static void mergeSort(Object src[], Object dest[], int low, int high, int off) {
    /* think of this as the template method */

    for (int i = low; i < high; i++) {
        // compareTo() is the method we need to implement to "fill out" the template method
        for (int j = i; j > low && ((Comparable)dest[j - i]).compareTo((Comparable)dest[j]) > 0; j--) {
            // this is a concrete method, already defined in the Arrays class
            swap(dest, j, j - 1);
        }
    }
    return;
}
```

The sort() method needs to know that you've implemented the compareTo() method.
The compareTo() method compares two objects and returns whether one is less than, greater than, or equal to the other.

- Implementation Code [Duck sorting](10_duck_sorting)

### Swinging with Frames

JFrame it's the most basic Swing container and inherits a paint() method (it's a hook method). By overriding paint(), you can insert yourself into JFrame's algorithm for displaying its area of the screen and have your own graphic output incorporated into the JFrame.

JFrame' update algorithm calls paint(). By default, paint() does nothing: it's a hook.

### Applets

Any applet must subclass Applet, and this class provides several hooks:

- the init() hook allows the applet to do whatever it wants to initialize the applet the first time
- the start() hook allows the applet to do something when the applet is just about to be displayed on the web page
- the stop() hook is used if the user goes to another page
- the destroy() hook is used when the applet is going to be destroyed
