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
