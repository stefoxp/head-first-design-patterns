package GumballMachineOneInTenGame;

/**
 * it's remarkably similar to the SoldState class
 * @author stefanop
 */
public class WinnerState implements State {
    /* just like SoldState */
    GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine) {
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

    
    /**
     * Here we release two gumballs 
     * and then either go to the NoQuarterState or the SoldOutState
     */
    @Override
    public void dispense() {
        System.out.println("YOU'RE A WINNER! You get two gumballs for your quarter");
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() == 0) {
            gumballMachine.setState(gumballMachine.getSoldOutState());
        } else {
            // as long as we have a second gumball we release it
            gumballMachine.releaseBall();
            if (gumballMachine.getCount() > 0) {
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            } else {
                System.out.println("Oops, out of gumballs!");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        }
    }
    
}
