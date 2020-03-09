package GumballMachineWithStatePattern;

public class HasQuarterState implements State {
    GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        // an inappropriate action for this state
        System.out.println("You can't insert another quarter");
    }

    @Override
    public void ejectQuarter() {
        // return the customer's quarter and transition back to the NoQuarterState
        System.out.println("Quarter returned");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        // we transition the machine to the SoldState state
        System.out.println("You turned...");
        gumballMachine.setState(gumballMachine.getSoldState());
    }

    @Override
    public void dispense() {
        // another inappropriate action
        System.out.println("No gumball dispensed");
    }
    
}
