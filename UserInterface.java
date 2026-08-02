public class UserInterface {
    
    public static void printTitle(String title) {
        System.out.println("\n" + "=".repeat(title.length() + 6));
        System.out.println(" ".repeat(3) + title + " ".repeat(3));
        System.out.println("=".repeat(title.length() + 6) + "\n");
    }

    public static void printFeedback(String message) {
        System.out.println("\n" + "-".repeat(message.length() + 6));
        System.out.println(" ".repeat(3) + message + " ".repeat(3));
        System.out.println("-".repeat(message.length() + 6) + "\n");
    }

    public static void menu() {
        printTitle("MENU OPTIONS");
       
        System.out.println("1. Add a Vehicle");
        System.out.println("2. View All Vehicles");
        System.out.println("3. Rent a Vehicle");
        System.out.println("4. Return a Vehicle");
        System.out.println("5. Exit\n");
    }

    public static void vehicleTypes() {
        printTitle("TYPE OF VEHICLE TO ADD");
       
        System.out.println("1. Car");
        System.out.println("2. Van");
        System.out.println("3. Motorcycle\n");
    }
}