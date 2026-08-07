import java.util.HashMap;

public class UserInterface {
    public static void printTitle(String title) {
        System.out.println("\n" + "=".repeat(title.length() + 6));
        System.out.println(" ".repeat(3) + title + " ".repeat(3));
        System.out.println("=".repeat(title.length() + 6) + "\n");
    }

    public static void printFeedback(String message) {
        if (message.contains("\n")) {
            String[] lines = message.split("\n");
            int longestLineLength = 0;

            for (String line : lines) {
                if (line.length() > longestLineLength) {
                    longestLineLength = line.length();
                }
            }

            System.out.println("\n" + "-".repeat(longestLineLength + 6));
            for (String line : lines) {
                System.out.println(" ".repeat(((longestLineLength - line.length()) / 2) + 3) + line);
            }
            System.out.println("-".repeat(longestLineLength + 6) + "\n");
            return;
        }
        
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
        int totalLengths = longestLengths[longestLengths.length - 1];

        System.out.println("\n" + "-".repeat(totalLengths + 31));

        System.out.print("| PLATE NUMBER" + " ".repeat(longestLengths[0] - 9));
        System.out.print("| TYPE" + " ".repeat(longestLengths[1] - 1));
        System.out.print("| STATUS" + " ".repeat(longestLengths[2] - 3));
        System.out.print("| MODEL" + " ".repeat(longestLengths[3] - 2));
        System.out.print("| BASE RATE" + " ".repeat(longestLengths[4] - 6));
        System.out.println("| DETAILS" + " ".repeat(longestLengths[5] - 4) + "|");
        
        System.out.println("-".repeat(totalLengths + 31));

        for (Vehicle vehicle : vehicles.values()) {
            String plateNumber = vehicle.getPlateNumber();
            VehicleType vehicleType = vehicle.getVehicleType();
            String status = (vehicle.getAvailability()) ? "AVAILABLE" : "RENTED";

            String model = vehicle.getModel();
            String ratePerDay = String.format("P%.2f", vehicle.getRatePerDay());
            String details = vehicle.getDetails();

            System.out.print("| " + plateNumber + " ".repeat(longestLengths[0] - plateNumber.length() + 3));
            System.out.print("| " + vehicleType + " ".repeat(longestLengths[1] - vehicleType.toString().length() + 3));
            System.out.print("| " + status + " ".repeat(longestLengths[2] - status.length() + 3));

            System.out.print("| " + model + " ".repeat(longestLengths[3] - model.length() + 3));
            System.out.print("| " + ratePerDay + " ".repeat(longestLengths[4] - ratePerDay.length() + 3));
            System.out.println("| " + details + " ".repeat(longestLengths[5] - details.length() + 3) + "|");
        }

        System.out.println("-".repeat(totalLengths + 31));
    }

    protected static void displayEmptyMessage() {
        printFeedback("Error! No Vehicles Found! Add Vehicles First.");
    }

    protected static void displayNoAvailableMessage() {
        printFeedback("Error! No Available Vehicles Found! All Vehicles Are Rented.");
    }

    protected static void displayNoRentedMessage() {
        printFeedback("Error! No Rented Vehicles Found! All Vehicles Are Available.");
    }

    // Helper method to get the length of the longest elements
    private static int[] getLengthLineElements (HashMap<String, Vehicle> vehicles) {
        int longestPlate = 12, longestType = 4, longestModel = 5, longestRate = 9, longestStatus = 6, longestDetails = 7;
                
        for (Vehicle vehicle : vehicles.values()) {
            int plateLength = vehicle.getPlateNumber().length();
            int typeLength = vehicle.getVehicleType().toString().length();
            int statusLength = (vehicle.getAvailability() ? "AVAILABLE" : "RENTED").length();

            int modelLength = vehicle.getModel().length();
            int rateLength = String.valueOf(vehicle.getRatePerDay()).length();
            int detailsLength = vehicle.getDetails().length();

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

            if (detailsLength > longestDetails) {
                longestDetails = detailsLength;
            }
        }

        int totalLengths = longestPlate + longestType + longestStatus + longestModel + longestRate + longestDetails;
        return new int[] {longestPlate, longestType, longestStatus, longestModel, longestRate, longestDetails, totalLengths};
    }
}