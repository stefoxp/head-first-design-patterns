package GumballMachineOneInTenGame;

import java.util.Random;

public class HasQuarterState implements State {
    // we add a random number generator to generate the 10% chance of winning
    Random randomWinner = new Random(System.currentTimeMillis());
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
        // then we determine if this customer won
        int winner = randomWinner.nextInt(10);
        // if they won, and there's enough gumballs left for them to get two
        // we go to the WinnerState
        // otherwise, we go to the SoldState
        if ((winner == 0) && (gumballMachine.getCount() > 1)) {
            gumballMachine.setState(gumballMachine.getWinnerState());
        } else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    }

    @Override
    public void dispense() {
        // another inappropriate action
        System.out.println("No gumball dispensed");
    }
    
}
