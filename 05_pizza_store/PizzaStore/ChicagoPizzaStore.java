package PizzaStore;

/**
 *
 * @author stefano
 */
public class ChicagoPizzaStore extends PizzaStore {
    Pizza createPizza(String type) {
        // for each type of Pizza we create the Chicago style
        if (type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        } else if (type.equals("pepperoni")) {
            return new ChicagoStylePepperoniPizza();
        } else return null;
    }
}
