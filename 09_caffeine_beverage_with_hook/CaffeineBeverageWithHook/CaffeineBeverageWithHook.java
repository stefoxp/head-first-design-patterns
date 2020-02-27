package CaffeineBeverageWithHook;

public abstract class CaffeineBeverageWithHook {
    void prepareRecipe() {
        boilWater();
        brew();
        powerInCup();

        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    abstract void brew();
    abstract void addCondiments();

    void boilWater() {
        System.out.println("Boiling water");
    }

    void powerInCup() {
        System.out.println("Pouring into cup");
    }

    // this is a hook because the subclass can override this method, but doesn't have to
    boolean customerWantsCondiments() {
        return true;
    }
}
