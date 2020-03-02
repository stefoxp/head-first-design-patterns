public class MenuTestDrive {
    public static void main(String args[]) {
        // we create the new menus
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();

        // we create a Waitress and pass her the menus
        Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);

        // print them
        waitress.printMenu();
    }
}
