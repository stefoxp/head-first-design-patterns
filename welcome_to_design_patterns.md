# 01 Welcome to Design Patterns

## Design principles

1. Identify the aspects of your application that vary and separate them (**encapsulate** it) from what stays the same
2. Program to an interface, not an implementation. Program to an interface really means Program to a **supertype** (abstract class or interface)

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

- Sample code: [Testing duck behaviors](01_duck_behavior)

3. Favor **composition** over inheritance
4. Strive for **loosely coupled designs** between objects that interact
