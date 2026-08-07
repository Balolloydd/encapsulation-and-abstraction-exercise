import java.util.HashMap;

public class RentalSystem { 
    private static final HashMap<String, Vehicle> vehicles = new HashMap<>();

    protected static void startProgram() {
        UserInterface.printTitle("VEHICLE RENTAL SYSTEM");
        boolean running = true;
        
        while (running) {
            UserInterface.menu();
            int choice = InputValidation.inputChoice("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1: addVehicle(); break;
                case 2: viewAllVehicles(); break;
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
        
        VehicleType vehicleType = switch (choice) {
            case 1 -> VehicleType.CAR;
            case 2 -> VehicleType.VAN;
            case 3 -> VehicleType.MOTORCYCLE;
            default -> null; // This case should never happen due to input validation
        };

        UserInterface.printTitle("ADD A " + vehicleType);
        String plateNumber = InputValidation.inputPlateNumber("Enter Plate Number: ", vehicleType, vehicles);
        String model = InputValidation.inputModel("Enter Model: ");
        double ratePerDay = InputValidation.inputRatePerDay("Enter Base Rate Per Day: ");

        if (vehicleType == VehicleType.CAR) {
            int numSeats = InputValidation.inputDetails("Enter Number of Seats: ", vehicleType);
            vehicles.put(plateNumber, new Car(plateNumber, model, ratePerDay, numSeats));
        } else if (vehicleType == VehicleType.VAN) {
            int cargoCapacity = InputValidation.inputDetails("Enter Cargo Capacity: ", vehicleType);
            vehicles.put(plateNumber, new Van(plateNumber, model, ratePerDay, cargoCapacity));
        } else if (vehicleType == VehicleType.MOTORCYCLE) {
            int engineDisplacement = InputValidation.inputDetails("Enter Engine Displacement: ", vehicleType);
            vehicles.put(plateNumber, new Motorcycle(plateNumber, model, ratePerDay, engineDisplacement));
        } else {
            UserInterface.printFeedback("Invalid Vehicle Type! Please try again.");
            return;
        }

        UserInterface.printFeedback("Vehicle Added Successfully!");
    }

    public static void viewAllVehicles() {
        if (vehicles.isEmpty()) {
            UserInterface.displayEmptyMessage();
            return;
        }

        UserInterface.displayVehicleTable(vehicles);
    }

    public static void rentVehicle() {
        if (vehicles.isEmpty()) {
            UserInterface.displayEmptyMessage();
            return;
        }

        if (!vehicles.values().stream().anyMatch(Vehicle::getAvailability)) {
            UserInterface.displayNoAvailableMessage();
            return;
        }

        UserInterface.printTitle("RENT A VEHICLE");
        String plateNumber = InputValidation.findPlateNumber("Enter Plate Number to Rent: ", vehicles);
        boolean isAvailable = vehicles.containsKey(plateNumber) && vehicles.get(plateNumber).getAvailability();

        if (!isAvailable) {
            UserInterface.printFeedback("Error! Vehicle Not Available! Please Try Again.");
            return;
        }

        int numberOfDays = InputValidation.inputPositiveInteger("Enter Number of Days to Rent: ");
        double rentalCost = vehicles.get(plateNumber).getRentalCost(numberOfDays);
        
        UserInterface.printFeedback(String.format("Rental Cost for %d days: P%.2f\nSuccessfully Rented Vehicle!", numberOfDays, rentalCost));
        vehicles.get(plateNumber).setAvailability(false);
    }

    public static void returnVehicle() {
        if (vehicles.isEmpty()) {
            UserInterface.displayEmptyMessage();
            return;
        }

        if (vehicles.values().stream().noneMatch(vehicle -> !vehicle.getAvailability())) {
            UserInterface.displayNoRentedMessage();
            return;
        }

        UserInterface.printTitle("RETURN A VEHICLE");
        String plateNumber = InputValidation.findPlateNumber("Enter Plate Number to Return: ", vehicles);
        boolean isRented = vehicles.containsKey(plateNumber) && !vehicles.get(plateNumber).getAvailability();

        if (!isRented) {
            UserInterface.printFeedback("Error! Vehicle Not Rented! Please Try Again.");
            return;
        }

        vehicles.get(plateNumber).setAvailability(true);
        UserInterface.printFeedback("Successfully Returned Vehicle!");
    }
}