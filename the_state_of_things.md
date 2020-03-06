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
