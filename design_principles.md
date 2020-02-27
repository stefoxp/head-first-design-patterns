# Design Principles

1. Identify the aspects of your application that vary and separate them (**encapsulate** it) from what stays the same
2. Program to an interface, not an implementation. Program to an interface really means Program to a **supertype** (abstract class or interface)
3. Favor **composition** over inheritance
4. Strive for **loosely coupled designs** between objects that interact
5. **Open-Closed** Principle: classes should be open for extension, but closed for modification
6. **Dependency Inversion** Principle: depend upon abstractions. Do not depend upon concrete classes
7. **Least Knowledge** Principle: talk only to your immediate friends (**Law of Demeter**)
8. **Hollywood** Principle: don't call us, we'll call you
