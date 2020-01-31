# 03 Decorating Objects

>Decorator Pattern

## Sample: Starbuzz Coffee

### Initial design

Their ordering systems:

```java
abstract class Beverage {
    description

    getDescription()
    abstract cost()

    // other methods
}

class HouseBlend extends Beverage {
    cost()
}

class DarkRoast extends Beverage {
    cost()
}

class Decaf extends Beverage {
    cost()
}

class Espresso extends Beverage {
    cost()
}
```

In addition to your coffee, you can also ask for several condiments like steamed milk, soy and mocha...
Starbuzz charges a bit for each of these, so they really need to get them built into their order system.

The first attempt is like this:

```java
class HouseBlendWithSteamedMilkandMocha extends Beverage {
    cost()
}

class EspressoWithSteamedMilkandMocha extends Beverage {
    cost()
}
...

```

### Inheritance solution

Add instance variables to represent whether or not each beverage has milk, soy, mocha... on the Beverage class.

```java
class Beverage {
    description
    milk
    soy
    mocha
    whip

    getDescription()
    cost()

    hasMilk()
    setMilk()
    hasSoy()
    setSoy()
    hasMocha()
    setMocha()
    hasWhip()
    setWhip()
}

// the superclass cost() will calculate the costs for all of the condiments, while the overridden cost() in the subclasses will extend that functionality to include costs for that specific beverage type.

class HouseBlend extends Beverage {
    cost()
}

class DarkRoast extends Beverage {
    cost()
}

class Decaf extends Beverage {
    cost()
}

class Espresso extends Beverage {
    cost()
}
```

Five class total.
Some potential problems with this approach by thinking about how the design might need to change in the future.

- **Open-Closed** Principle

There are techniques for allowing code to be extended without direct modification.

N.B. applying the Open-Closed Principle everywhere is wasteful, unnecessary, and can lead to complex, hard to understand code.

### Introduce the Decorator Pattern

- Decorators have the same supertype as the objects they decorate;
- You can use one or more decorators to wrap an object;
- Given that the decorator has the same supertype as the object it decorates, we can pass around a decorated object in place of the original (wrapped) object;
- The decorator adds its own behavior either before and / or after delegating to the object it decorates to do the rest of the job;
- Objects can be decorated at any time, so we can decorate objects dynamically at runtime with as many decorators as we like;

- **Decorator Pattern**

#### A class diagram of the Decorator Pattern

```java
class Component {
    methodA()
    methodB()

    // other methods
}

class ConcreteComponent extends Component {
    methodA()
    methodB()

    // other methods
}

// Each decorator HAS-A (wraps) a component (the decorator has an instance variable that holds a reference to a component)

// Decorators implement the same interface or abstract class as the component they are going to decorate

// component of Component
class Decorator extends Component {
    methodA()
    methodB()

    // other methods
}

class ConcreteDecoratorA extends Decorator {
    // instance variable for the thing it decorates
    Component wrappedObj

    methodA()
    methodB()
    // can add new methods. However, new behavior is typically added by doing computation before or after an existing method in the component
    newBehavior()

    // other methods
}

class ConcreteDecoratorB extends Decorator {
    Component wrappedObj
    // can extend the state of the component
    Object newState

    methodA()
    methodB()

    // other methods
}
```

#### Starbuzz sample with Decorator Pattern

```java
abstract class Beverage {
    description

    getDescription()
    cost()

    // other methods
}

// the four concrete components, one per coffee type
class HouseBlend extends Beverage {
    cost()
}

class DarkRoast extends Beverage {
    cost()
}

class Espresso extends Beverage {
    cost()
}

class Decaf extends Beverage {
    cost()
}

// component of Beverage
class CondimentDecorator extends Beverage {
    getDescription()
}

class Milk extends CondimentDecorator {
    Beverage beverage

    cost()
    getDescription()
}

class Mocha extends CondimentDecorator {
    Beverage beverage

    cost()
    getDescription()
}

class Soy extends CondimentDecorator {
    Beverage beverage

    cost()
    getDescription()
}

class Whip extends CondimentDecorator {
    Beverage beverage

    cost()
    getDescription()
}
```

- Implementation Code [Starbuzz Coffee](03_starbuzz_coffee)

java.io package is largely based on Decorator Pattern.

Class diagram:

```java
// abstract component
abstract class InputStream {}

// the concrete components that we will wrap with decorators
class FileInputStream extends InputStream {}
class StringBufferInputStream extends InputStream {}
class ByteArrayInputStream extends InputStream {}

// abstract decorator
abstract class FilterInputStream extends InputStream {}

// concrete decorators
class PushbackInputStream extends FilterInputStream {}
class BufferedInputStream extends FilterInputStream {}
class DataInputStream extends FilterInputStream {}
class LineNumberInputStream extends FilterInputStream {}
```

java.io also points out one of the downsides of the Decorator Pattern: designs using this pattern often result in a large number of small classes that can be overwhelming to a developer trying to use the Decorator-based API.

- Implementation Code [your Java I/O Decorator](04_java_io_decorator)
