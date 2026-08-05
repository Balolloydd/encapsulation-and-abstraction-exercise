import java.util.HashMap;
import java.util.HashSet;

public class RentalSystem {
    private static final HashMap<String, Vehicle> vehicles = new HashMap<>();
    private static final HashSet<String> availablePlateNumbers = new HashSet<>();
    private static final HashSet<String> rentedPlateNumbers = new HashSet<>();

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
                case 3: rentVehicle(); break;
                case 4: returnVehicle(); break;
                case 5:
                    running = false;
                    UserInterface.printFeedback("Exiting the program. Goodbye!");
                    break;
                default: UserInterface.printFeedback("Invalid Input! Please try again.");
            }
        }

        InputValidation.closeScanner();
    }

    public static void addVehicle() {
        UserInterface.vehicleTypes();
        int choice = InputValidation.inputChoice("Enter your choice: ", 1, 3);
        String vehicleType = "";

        switch (choice) {
            case 1: vehicleType = "Car"; break;
            case 2: vehicleType = "Van"; break;
            case 3: vehicleType = "Motorcycle"; break;
            default: UserInterface.printFeedback("Invalid Input! Please try again."); return;
        }

        String plateNumber = InputValidation.inputPlateNumber("Enter Plate Number: ");
        String model = InputValidation.inputModel("Enter Model: ");
        double ratePerDay = InputValidation.inputRatePerDay("Enter Base Rate Per Day: ");

        if (vehicleType.equalsIgnoreCase("Car")) {
            int numSeats = InputValidation.inputPositiveInteger("Enter Number of Seats: ");
            vehicles.put(plateNumber, new Car(plateNumber, model, ratePerDay, numSeats));
        } else if (vehicleType.equalsIgnoreCase("Van")) {
            int cargoCapacity = InputValidation.inputPositiveInteger("Enter Cargo Capacity: ");
            vehicles.put(plateNumber, new Van(plateNumber, model, ratePerDay, cargoCapacity));
        } else if (vehicleType.equalsIgnoreCase("Motorcycle")) {
            int engineDisplacement = InputValidation.inputPositiveInteger("Enter Engine Displacement: ");
            vehicles.put(plateNumber, new Motorcycle(plateNumber, model, ratePerDay, engineDisplacement));
        } else {
            UserInterface.printFeedback("Invalid Vehicle Type! Please try again.");
            return;
        }

        availablePlateNumbers.add(plateNumber);
        UserInterface.printFeedback("Vehicle added successfully!");
    }

    public static void rentVehicle() {
        if (vehicles.isEmpty()) {
            UserInterface.displayEmptyMessage();
            return;
        }
        
        if (availablePlateNumbers.isEmpty()) {
            UserInterface.displayNoAvailableMessage();
            return;
        }

        String plateNumber = searchPlateNumber();
        if (plateNumber == null) {
            return;
        }

        int numberOfDays = InputValidation.inputPositiveInteger("Enter Number of Days to Rent: ");
        double rentalCost = vehicles.get(plateNumber).rentalCost(numberOfDays);
        UserInterface.printFeedback(("Successfully Rented Vehicle! Rental Cost: P" + String.format("%.2f", rentalCost)));
        
        availablePlateNumbers.remove(plateNumber);
        rentedPlateNumbers.add(plateNumber);
        vehicles.get(plateNumber).setStatus(false);
    }

    public static void returnVehicle() {
        if (vehicles.isEmpty()) {
            UserInterface.displayEmptyMessage();
            return;
        }
        
        if (rentedPlateNumbers.isEmpty()) {
            UserInterface.printFeedback("Error! No Rented Vehicles Found! All Vehicles Are Available.");
            return;
        }

        String plateNumber = InputValidation.inputPlateNumber("Enter Plate Number to Return: ");
        boolean found = rentedPlateNumbers.contains(plateNumber);

        if (!vehicles.containsKey(plateNumber)) {
            UserInterface.printFeedback("Error! Vehicle with Plate Number " + plateNumber + " does not exist.");
            return;
        }

        if (!found) {
            UserInterface.printFeedback("Error! Vehicle with Plate Number " + plateNumber + " is not currently rented.");
            return;
        }

        availablePlateNumbers.add(plateNumber);
        rentedPlateNumbers.remove(plateNumber);
        
        vehicles.get(plateNumber).setStatus(true);
        UserInterface.printFeedback("Successfully Returned Vehicle!");
    }

    // Helper method to search for a vehicle by plate number and check if it's available for rent
    public static String searchPlateNumber() { 
        if (availablePlateNumbers.isEmpty()) {
            UserInterface.displayNoAvailableMessage();
            return null;
        }

        String plateNumber = InputValidation.inputPlateNumber("Enter Plate Number: ");
        boolean found = availablePlateNumbers.contains(plateNumber);

        if (!vehicles.containsKey(plateNumber)) {
            UserInterface.printFeedback("Error! Vehicle with Plate Number " + plateNumber + " does not exist.");
            return null;
        }

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