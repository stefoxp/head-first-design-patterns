# 1 Welcome to Design Patterns

## Design principles (a)

a1. Identify the aspects of your application that vary and separate them (**encapsulate** it) from what stays the same
a2. Program to an interface, not an implementation. Program to an interface really means Program to a **supertype** (abstract class or interface)

Example:

i) Programming to an implementation

```java
Dog d = new Dog();
d.bark();
```

ii) Programming to an interface/supertype

```java
Animal animal = new Dog();
// use the animal reference polymorphically
animal.makeSound();
```

iii) assign the concrete implementation object at runtime

```java
a = getAnimal();
animal.makeSound();
```

[Testing duck behaviors](testing_duck_behaviors.md)

a3. Favor **composition** over inheritance

## Design Patterns (b)

Design Patterns give you a **shared vocabulary** with other developers:

- shared pattern vocabularies are powerful
- pattern allow you to say more with less
- talking at the pattern level allows you to stay "in the design" longer
- shared vocabularies can turbo charge your development team
- shared vocabularies encourage more junior developers to get up to speed

b1. the Strategy Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.
