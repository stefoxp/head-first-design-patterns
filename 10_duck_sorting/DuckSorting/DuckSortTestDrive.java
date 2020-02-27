import java.util.Arrays;

public class DuckSortTestDrive {
    public static void main(String[] args) {
        // we need an array of Ducks
        Duck[] ducks = {
                new Duck("Daffy", 8),
                new Duck("Dewey", 2),
                new Duck("Howard", 7),
                new Duck("Louie", 2),
                new Duck("Donald", 10),
                new Duck("Huey", 2)
        };

        // let's print them to see their names and weights
        System.out.println("Before sorting");
        display(ducks);

        // it's sort time

        // we call Array's static method sort, and pass it our Ducks
        Arrays.sort(ducks);

        System.out.println("\nAfter sorting:");
        display(ducks);
    }

    private static void display(Duck[] ducks) {
        for  (int i = 0; i < ducks.length; i++) {
            System.out.println(ducks[i]);
        }
    }
}
