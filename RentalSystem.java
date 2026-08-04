import java.util.HashMap;

public class RentalSystem {
    private static final HashMap<String, Vehicle> vehicles = new HashMap<>();
    private static final HashMap<String, Vehicle> rentedVehicles = new HashMap<>();
    private static final HashMap<String, Vehicle> availableVehicles = new HashMap<>();

    protected static void start() {
        UserInterface.printTitle("VEHICLE RENTAL SYSTEM");
        
        HashMap<String, Vehicle> vehicles = getVehiclesList();
        boolean running = true;
        
        while (running) {
            UserInterface.menu();
            int choice = InputValidation.inputChoice("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1: addVehicle(); break;
                case 2: UserInterface.displayVehicleTable(vehicles); break;
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

    public static void addVehicle() {
        UserInterface.vehicleTypes();
        int choice = InputValidation.inputChoice("Enter your choice: ", 1, 3);
        String vehicleType = "";

        switch (choice) {
            case 1:
                vehicleType = "Car";
                break;
            case 2:
                vehicleType = "Van";
                break;
            case 3:
                vehicleType = "Motorcycle";
                break;
            default:
                UserInterface.printFeedback("Invalid Input! Please try again.");
                return;
        }

        String plateNumber = InputValidation.inputPlateNumber("Enter Plate Number: ");
        String model = InputValidation.inputModel("Enter Model: ");
        double ratePerDay = InputValidation.inputRatePerDay("Enter Base Rate Per Day: ");

        if (vehicleType.equalsIgnoreCase("Car")) {
            int numSeats = InputValidation.inputPositiveInteger("Enter Number of Seats: ");
            
            vehicles.put(plateNumber, new Car(plateNumber, model, ratePerDay, numSeats));
            availableVehicles.put(plateNumber, new Car(plateNumber, model, ratePerDay, numSeats));
        } else if (vehicleType.equalsIgnoreCase("Van")) {
            int cargoCapacity = InputValidation.inputPositiveInteger("Enter Cargo Capacity: ");
            
            vehicles.put(plateNumber, new Van(plateNumber, model, ratePerDay, cargoCapacity));
            availableVehicles.put(plateNumber, new Van(plateNumber, model, ratePerDay, cargoCapacity));
        } else if (vehicleType.equalsIgnoreCase("Motorcycle")) {
            int engineDisplacement = InputValidation.inputPositiveInteger("Enter Engine Displacement: ");
            
            vehicles.put(plateNumber, new Motorcycle(plateNumber, model, ratePerDay, engineDisplacement));
            availableVehicles.put(plateNumber, new Motorcycle(plateNumber, model, ratePerDay, engineDisplacement));
        } else {
            UserInterface.printFeedback("Invalid Vehicle Type! Please try again.");
            return;
        }

        UserInterface.printFeedback("Vehicle added successfully!");
    }

    public static void rentVehicle(String plateNumber, int numberOfDays) {
        if (vehicles.isEmpty()) {
            UserInterface.displayEmptyMessage();
            return;
        }
        
        if (availableVehicles.isEmpty()) {
            UserInterface.displayNoAvailableMessage();
            return;
        }
    }

    public static String searchPlateNumber() {
        if (vehicles.isEmpty()) {
            UserInterface.displayEmptyMessage();
            return null;
        }
        
        if (availableVehicles.isEmpty()) {
            UserInterface.displayNoAvailableMessage();
            return null;
        }

        String plateNumber = InputValidation.inputPlateNumber("Enter Plate Number: ");
        boolean found = availableVehicles.containsKey(plateNumber);

        if (!found) {
            UserInterface.printFeedback("Error! Vehicle with Plate Number " + plateNumber + " is not available.");
            return null;
        }

        return plateNumber;
    }

    // Helper method for other classes (inside package) to access the vehicles list
    protected static HashMap<String, Vehicle> getVehiclesList() {
        return vehicles;
    }
}