# Head First - Design Patterns

Eric Freeman & Elisabeth Freeman

## Welcome to Design Patterns

### Design principles

- Identify the aspects of your application that vary and separate them (**encapsulate** it) from what stays the same
- Program to an interface, not an implementation. Program to an interface really means Program to a supertype (abstract class or interface)

Example:

a) Programming to an implementation

```java
Dog d = new Dog();
d.bark();
```

b) Programming to an interface/supertype

```java
Animal animal = new Dog();
// use the animal reference polymorphically
animal.makeSound();
```

c) assign the concrete implementation object at runtime

```java
a = getAnimal();
animal.makeSound();
```

[Testing duck behaviors](testing_duck_behaviors.md)

