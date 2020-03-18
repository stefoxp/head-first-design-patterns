package GumballMachineOneInTenGame;

/**
 * this code really hasn't changed at all
 * we just shortened it a bit
 * @author stefanop
 */
public class GumballMachineTestDrive {
    public static void main(String[] args) {
        // start with a gumball machine with 5 gumballs
        GumballMachine gumballMachine = new GumballMachine(5);
        
        System.out.println(gumballMachine);
        
        /* 
        we want to get a winning state, 
        so we just keep pumping in those quarters 
        and turning the crank
        We print out the state of the gumball machine every so often
        */
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        
        System.out.println(gumballMachine);
        
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        
        System.out.println(gumballMachine);
    }
}
