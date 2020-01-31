package PizzaStore;

/**
 *
 * @author stefano
 */
public class NYPizzaStore extends PizzaStore {
    Pizza createPizza(String type) {
        // for each type of Pizza we create the NY style
        if (type.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else if (type.equals("pepperoni")) {
            return new NYStylePepperoniPizza();
        } else return null;
    }
}
