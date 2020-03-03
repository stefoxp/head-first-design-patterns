package Cafe;

public class MenuTestDrive {
    public static void main(String args[]) {
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();
        CafeMenu cafeMenu = new CafeMenu();
        
        Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu, cafeMenu);
        
        // when we print we should see all three menus
        waitress.printMenu();
    }
}
