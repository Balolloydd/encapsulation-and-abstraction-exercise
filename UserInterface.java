import java.util.HashMap;

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
        printTitle("TYPE OF VEHICLES");
       
        System.out.println("1. Car");
        System.out.println("2. Van");
        System.out.println("3. Motorcycle\n");
    }

    public static void displayVehicleTable(HashMap<String, Vehicle> vehicles) {
        int[] longestLengths = getLengthLineElements(vehicles);

        System.out.println("-".repeat(longestLengths[0] + longestLengths[1] + longestLengths[2] + longestLengths[3] + longestLengths[4] + 19));

        System.out.printf("| PLATE NUMBER" + " ".repeat(longestLengths[0] - 12));
        System.out.printf("| TYPE" + " ".repeat(longestLengths[1] - 4));
        System.out.printf("| MODEL" + " ".repeat(longestLengths[2] - 5));
        System.out.printf("| BASE RATE" + " ".repeat(longestLengths[3] - 9));
        System.out.printf("| STATUS" + " ".repeat(longestLengths[4] - 6) + "|\n");

        System.out.println("-".repeat(longestLengths[0] + longestLengths[1] + longestLengths[2] + longestLengths[3] + longestLengths[4] + 19));

        for (Vehicle vehicle : vehicles.values()) {
            String plateNumber = vehicle.getPlateNumber();
            String vehicleType = vehicle.getVehicleType();
            
            String model = vehicle.getModel();
            double ratePerDay = vehicle.getRatePerDay();
            String status = (vehicle.getStatus()) ? "AVAILABLE" : "RENTED";

            System.out.printf("| " + plateNumber + " ".repeat(longestLengths[0] - plateNumber.length() + 3));
            System.out.printf("| " + vehicleType + " ".repeat(longestLengths[1] - vehicleType.length() + 3));
            System.out.printf("| " + model + " ".repeat(longestLengths[2] - model.length() + 3));
            System.out.printf("| " + ratePerDay + " ".repeat(longestLengths[3] - String.valueOf(ratePerDay).length() + 3));
            System.out.printf("| " + status + " ".repeat(longestLengths[4] - status.length() + 3) + "|\n");
        }

        System.out.println("-".repeat(longestLengths[0] + longestLengths[1] + longestLengths[2] + longestLengths[3] + longestLengths[4] + 19));
    }

    protected static void displayEmptyMessage() {
        printFeedback("Error! No Vehicles Found! Add Vehicles First.");
    }

    protected static void displayNoAvailableMessage() {
        printFeedback("Error! No Available Vehicles Found! All Vehicles Are Rented.");
    }

    // Helper method to get the length of the longest elements
    private static int[] getLengthLineElements (HashMap<String, Vehicle> vehicles) {
        int longestPlate = 12, longestType = 4, longestModel = 5, longestRate = 9, longestStatus = 6;
                
        for (Vehicle vehicle : vehicles.values()) {
            int plateLength = vehicle.getPlateNumber().length();
            int modelLength = vehicle.getModel().length();
            int typeLength = vehicle.getVehicleType().length();
            int rateLength = String.valueOf(vehicle.getRatePerDay()).length();
            int statusLength = String.valueOf(vehicle.getStatus()).length();

            if (plateLength > longestPlate) {
                longestPlate = plateLength;
            }

            if (modelLength > longestModel) {
                longestModel = modelLength;
            }

            if (typeLength > longestType) {
                longestType = typeLength;
            }

            if (rateLength > longestRate) {
                longestRate = rateLength;
            }

            if (statusLength > longestStatus) {
                longestStatus = statusLength;
            }
        }
        return new int[] {longestPlate, longestType, longestModel, longestRate, longestStatus};
    }
}