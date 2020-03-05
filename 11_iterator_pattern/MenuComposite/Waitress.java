package MenuComposite;

public class Waitress {
    MenuComponent allMenus;
    
    // the code really is this simple
    // now we just hand her the top level menu component => allMenus
    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }
    
    // print the entire menu hierarchy
    public void printMenu() {
        allMenus.print();
    }
}
