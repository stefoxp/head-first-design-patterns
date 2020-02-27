package CaffeineBeverageWithHook;

public class BeverageTestDrive {
    public static void main(String[] args) {
        
        // create a tea
        TeaWithHook teaHook = new TeaWithHook();
        // a coffee
        CoffeeWithHook coffeeHook = new CoffeeWithHook();
        
        // call the template method on both
        System.out.println("\nMaking tea...");
        teaHook.prepareRecipe();
        
        System.out.println("\nMaking coffee...");
        coffeeHook.prepareRecipe();
    }
}