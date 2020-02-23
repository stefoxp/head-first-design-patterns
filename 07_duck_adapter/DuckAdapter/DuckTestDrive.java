package DuckAdapter;

public class DuckTestDrive {
    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();
        WildTurkey turkey = new WildTurkey();
        
        // wrap the turkey in a TurkeyAdapter which makes it look like a Duck
        Duck turkeyAdapter = new TurkeyAdapter(turkey);
        
        // let's test the Turkey
        System.out.println("The Turkey says...");
        turkey.gobble();
        turkey.fly();
        
        // test the duck
        System.out.println("\nThe Duck says...");
        testDuck(duck);
        
        // the big test: we try to pass off the turkey as a duck
        System.out.println("\nThe TurkeysAdapter says...");
        testDuck(turkeyAdapter);
    }
    
    static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
