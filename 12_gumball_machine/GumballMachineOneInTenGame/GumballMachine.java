package GumballMachineOneInTenGame;

public class GumballMachine {
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;
    // all you need to add here is the new winnerState
    // and initialize it in the constructor
    State winnerState;
    
    State state = soldOutState;
    int count = 0;
    
    // methods here
    // takes an initial inventory of gumballs
    public GumballMachine(int numberGumballs) {
        // creates the State instances, one of each
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        
        this.count = numberGumballs;
        // if the inventory isn't zero, the machine enters state NO_QUARTER
        if (count > 0) {
            state = noQuarterState;
        }
    }
    
    /* actions 
        These are VERY EASY to implement now: we just delegate
    */
    
    public void insertQuarter() {
        state.insertQuarter();
    }
    
    public void ejectQuarter() {
        state.ejectQuarter();
    }
    
    public void turnCrank() {
        state.turnCrank();
        // we don't need an action method for dispense() GumballMachine
        // because it's just an internal action
        state.dispense();
    }
    
    /**
     * allows other objects to transition the machine to a different state
     * @param state 
     */
    void setState(State state) {
        this.state = state;
    }
    
    /**
     * helper method that releases the ball and decrements the count instance variable
     */
    void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count = count - 1;
        }
    }
    
    // more methods here including getters for each State...
    
    // other methods here like toString() and refill()
    public String toString() {
        String waitingMessage = "";
        waitingMessage += "\nMighty Gumball, Inc\n";
        waitingMessage += "Java-enabled Standing Gumball Model #2004\n";
        waitingMessage += "Inventory: " + Integer.toString(count) + " gumballs\n";
        waitingMessage += "Machine is waiting for quarter\n";
        
        return waitingMessage;
    }

    State getHasQuarterState() {
        return hasQuarterState;
    }

    State getNoQuarterState() {
        return noQuarterState;
    }

    State getSoldState() {
        return soldState;
    }

    public int getCount() {
        return count;
    }

    State getSoldOutState() {
        return soldOutState;
    }
    /* don't forget you also have to add a getter method for winnerState too */

    State getWinnerState() {
        return winnerState;
    }
}
