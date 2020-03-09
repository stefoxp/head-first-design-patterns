package GumballMachineWithStatePattern;

public class GumballMachine {
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;
    
    // the instance variable
    State state = soldOutState;
    // keeps track of the number of gumballs in the machine
    int count = 0;
    
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

    public int getHasQuarterState() {
        // TODO: implementation code
        throw new UnsupportedOperationException("Not supported yet.");
    }

    State getNoQuarterState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    State getSoldState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCount() {
        return count;
    }

    State getSoldOutState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
