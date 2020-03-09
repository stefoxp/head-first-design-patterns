package GumballMachineWithStatePattern;

public class NoQuarterState implements State {
    GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
    
    @Override
    public void insertQuarter() {
        // we print a message
        System.out.println("You inserted a quarter");
        // and then change the machine's state to HasQuarterState
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        // you can't get money back if you never gave it to us
        System.out.println("You haven't inserted a quarter");
    }

    @Override
    public void turnCrank() {
        // you can't get a gumball if you don't pay us
        System.out.println("You turned, but there's no quarter");
    }

    @Override
    public void dispense() {
        // we can't be dispensing gumballs without payment
        System.out.println("You need to pay first");
    }
    
}
