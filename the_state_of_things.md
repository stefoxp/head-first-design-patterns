# The State of Things

## The Gumball machine sample

The gumball machine has four states:

- no quarter
- has quarter
- out of gumballs
- gumball sold

We can hold the states with an instance variable:

```java
// each state is represented as a unique integer
final static int SOLD_OUT = 0;
final static int NO_QUARTER = 1;
final static int HAS_QUARTER = 2;
final static int SOLD = 3;

// instance variable
int state = SOLD_OUT;
```

The actions that can happen in the system is:

- insert quarter
- eject quarter
- turns crank
- dispense

invoking any of these actions causes a state transition.
These actions are the gumball machine's interface. Dispense is more of an internal action.

Now  we create a class that acts as the state machine. For each action, we create a method that uses conditional statements to determine what behavior is appropriate in each state.

```java
// for the insert quarter action
public void insertQuarter() {
    // each possible state is checked with a conditional statement
    if (state == SOLD_OUT) {
        // and exhibits the appropriate behavior for each possible state
        System.out.println("You can't insert another quarter");
    } else if (state == SOLD_OUT) {
        System.out.println("You can't insert a quarter, the machine is sold out");
    } else if (state == SOLD) {
        System.out.println("Please wait, we're already giving you a gumball");
    } else if (state == NO_QUARTER) {
        // but can also transition to other states
        state = HAS_QUARTER;
        System.out.println("You inserted a quarter");
    }
}
```

- Implementation Code [Gumball Machine](12_gumball_machine)

### A change request

Turning gumball buying into a game.

The code isn't easy to extend:

- first, you'd have to add a new WINNER state
- then, you'd have to add a new conditional in every single method to handle the WINNER state
- turnCrank() will get especially messy, because you'd have to add code to check to see whether you've got a WINNER and then switch to either the WINNER state or the SOLD state

### New design

Rework the code to encapsulate state objects in their own classes and then delegate to the current state when an action occurs.

1. define a State interface that contains a method for every action in the Gumball Machine
2. implement a State class for every state of the machine
3. get rid of all of our conditional code and instead delegate to the state class to do the work for us

Not only are we following design principles, we're actually implementing the **State Pattern**.

### New design - class diagram

```java
interface State {
    // the methods map directly to actions that could happen to the Gumball Machine
    insertQuarter()
    ejectQuarter()
    turnCrank()
    dispense()
}

class SoldState implements State {
    // tell the customer, "Please wait, we're already giving you a gumball"
    insertQuarter()
    ejectQuarter()
    turnCrank()
    // dispense a gumball. Check number of gumball. If...
    dispense()
}

class SoldOutState implements State {
    insertQuarter()
    ejectQuarter()
    // tell the customer, "There are no gumballs"
    turnCrank()
    dispense()
}

class NoQuarterState implements State {
    // go to HasQuarterState
    insertQuarter()
    // tell the customer, "You haven't inserted a quarter"
    ejectQuarter()
    turnCrank()
    dispense()
}

class HasQuarterState implements State {
    insertQuarter()
    ejectQuarter()
    // go to SoldState
    turnCrank()
    dispense()
}

/* a new WINNER state  */
class WinnerState implements State {
    insertQuarter()
    ejectQuarter()
    turnCrank()
    // go ahead and fill this out even though we're implementing it later
    dispense()
}
```

- Implementation Code [Gumball Machine with State Pattern](12_gumball_machine)

You now have a Gumball Machine implementation that is structurally quite different from your first version, and yet functionally it is exactly the same.

Structural changes, you've:

- localized the behavior of each state into its own class
- removed all the troublesome **if** statements that would have been difficult to maintain
- closed each state for modification, and yet left the Gumball Machine open to extension by adding new state classes
- created a code base and class structure that maps much more closely to the Mighty Gumball diagram and is easier to read and understand

Functional aspects:

- the Gumball Machine now holds an instance of each State class
  - the current state of the machine is always one of these class instance
- when an action is called, it is delegated to the current state
- the machine enters the Sold state and a gumball is dispensed and then the machine will either go to the SoldOut or NoQuarter state depending on the number of gumball remaining in the machine

## The State Pattern

- Definition of the **State** Pattern

```java
/* class diagram */

// the State interface defines a common interface for all concrete states (the states are interchangeable)
class State {
    handle()
}

// handle requests from the Context.
// Each ConcreteState provides its own implementation for a request
// when the Context changes state, its behavior will change as well
class ConcreteStateA extends State {
    handle()
}
class ConcreteStateB extends State {
    handle()
}

// is the class that can have a number of internal states
class Context {
    // whenever the request() is made on the Context it is delegated to the state to handle -> state.handle()
    request()
}
```

This class diagram is exactly the same we use for the **Strategy** Pattern.
The two patterns differ in their intent.

In general, think of the Strategy Pattern as a flexible alternative to sub-classing. With Strategy you can change the behavior by composing with a different object.
Think of the State Pattern as an alternative to putting lots of conditionals in your context; by encapsulating the behaviors within state objects, you can simply change the state object in context to change its behavior.

- Implementation Code [Gumball Machine 1 in 10 Game](12_gumball_machine)

Some aspects of the Gumball Machine:

- we've got a lot of duplicate code in the Sold and Winning states and we might want to clean those up.
We could make State into an abstract class and build in some default behavior for the methods.
- the dispense() method always gets called. We could easily fix this by having turnCrank() return a boolean, or by introducing exceptions.
- all of the intelligence for the state transitions is in the State classes
- if you will be instantiating a lot of GumballMachine objects, you may want to move the state instances into static instance variables and share them
