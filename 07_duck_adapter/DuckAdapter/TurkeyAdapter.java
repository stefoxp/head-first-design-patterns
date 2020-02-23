package DuckAdapter;

// first, we need to implement the interface of the type you're adapting to
// This is the interface your client expect to see
public class TurkeyAdapter implements Duck {
    Turkey turkey;
    
    // next we need to get a reference to the object that we are adapting
    // here we do that through the constructor
    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    // now, we need to implement all the methods in the interface
    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        // Turkeys fly in short spurts
        // to map between a Duck's fly() method and a Turkey's, 
        // we need to call the Turkey's fly() method five times to make up for it
        for(int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
    
}
