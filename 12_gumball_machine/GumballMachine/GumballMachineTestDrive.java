package GumballMachine;

public class GumballMachineTestDrive {
    public static void main(String[] args) {
        // load it up with five gumballs total
        GumballMachine gumballMachine = new GumballMachine(5);
        
        // print out the state of the machine
        System.out.println(gumballMachine);
        
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        
        System.out.println(gumballMachine);
        
        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();
        gumballMachine.turnCrank();
        
        System.out.println(gumballMachine);
        
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.ejectQuarter();
        
        System.out.println(gumballMachine);
        
        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        
        System.out.println(gumballMachine);
    }
}
