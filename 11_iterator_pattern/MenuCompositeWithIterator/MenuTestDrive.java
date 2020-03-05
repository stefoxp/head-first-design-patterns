package MenuCompositeWithIterator;

public class MenuTestDrive {
    public static void main(String args[]) {
        // we create all the menu objects
        MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
        MenuComponent dinerMenu = new Menu("DINER MENU", "Lunch");
        MenuComponent cafeMenu = new Menu("CAFE MENU", "Dinner");
        MenuComponent dessertMenu = new Menu("DESSERT MENU", "Dessert of course!");
        
        // we need two top level menu
        MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");
        
        // we add each menu to the top level menu
        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinerMenu);
        allMenus.add(cafeMenu);
        
        // add menu items here
        // (now we need to add all the menu items, here's one example)
        dinerMenu.add(new MenuItem(
                "Pasta",
                "Spaghetti with Marinara Sauce, and a slice of sourdough bread",
                true,
                3.89));
        
        // add menu to a menu
        dinerMenu.add(dessertMenu);
        
        // add sub-menu items
        dessertMenu.add(new MenuItem(
                "Apple Pie", 
                "Apple pie with a flakey crust, topped with vanilla icecream",
                true,
                1.59));
        
        // we hand the whole thing to the Waitress
        Waitress waitress = new Waitress(allMenus);
        
        waitress.printVegetarianMenu();
    }
}
