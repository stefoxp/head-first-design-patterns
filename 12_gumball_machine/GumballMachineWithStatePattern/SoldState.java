package GumballMachineWithStatePattern;

public class SoldState implements State {
    GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        // inappropriate action
        System.out.println("Please wait, we're already giving you a gumball");
    }

    @Override
    public void ejectQuarter() {
        // inappropriate action
        System.out.println("Sorry, you already turned the crank");
    }

    @Override
    public void turnCrank() {
        // inappropriate action
        System.out.println("Turning twice doesn't get you another gumball!");
    }

    @Override
    public void dispense() {
        // here's where the real work begins
        // we first need to ask the machine to release a gumball
        gumballMachine.releaseBall();
        // we ask the machine what the gumball count is
        if (gumballMachine.getCount() > 0) {
            // transition to NoQuarterState
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            // transition to SoldOutState
            System.out.println("Oops, out of gumballs!");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
    
}
