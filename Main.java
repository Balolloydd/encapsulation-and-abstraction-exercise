import java.util.ArrayList;

public class Main {
    private static final ArrayList<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        UserInterface.printTitle("VEHICLE RENTAL SYSTEM");
        startProgram();
        InputValidation.closeScanner();
    }

    private static void startProgram() {
        boolean running = true;
        
        while (running) {
            UserInterface.menu();
            int choice = InputValidation.inputChoice("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1:
                    UserInterface.vehicleTypes();
                    String vehicleType = RentalSystem.getVehicleType();
                    
                    vehicles.add(RentalSystem.addVehicle(vehicleType));
                    UserInterface.printFeedback("Vehicle added successfully!");
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                   
                    break;
                case 5:
                    running = false;
                    UserInterface.printFeedback("Exiting the program. Goodbye!");
                    break;
                default:
                    UserInterface.printFeedback("Invalid Input! Please try again.");
            }
        }
    }

    // Helper method for other classes (inside package) to access the vehicles list
    protected static ArrayList<Vehicle> getVehiclesList() {
        return vehicles;
    }
}