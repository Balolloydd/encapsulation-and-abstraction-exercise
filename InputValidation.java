import java.util.Scanner;
import java.util.HashMap;

public class InputValidation {
    private static Scanner sc = new Scanner(System.in);

    public static int inputChoice(String prompt, int min, int max) {
        boolean isValid = false;
        String choice = "";
        
        while (!isValid) {
            System.out.print(prompt);
            choice = sc.nextLine().trim();
            
            if (choice.matches(String.format("[%d-%d]", min, max))) {
                isValid = true;
            } else {
                UserInterface.printFeedback("Invalid Input! Please enter a number between " + min + " and " + max + ".");
            }
        }

        return Integer.parseInt(choice);
    }

    // For adding plate numbers
    public static String inputPlateNumber(String prompt, VehicleType vehicleType, HashMap<String, Vehicle> vehicles) {
        boolean isValid = false;
        String input = "";
        
        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            
            if (vehicles.containsKey(input)) {
                UserInterface.printFeedback("Invalid Input! Plate Number already Exists.");
            } else if ((vehicleType.equals(VehicleType.CAR) || vehicleType.equals((VehicleType.VAN))) && !input.matches("^[A-Z]{3}[0-9]{3,4}$")) {
                UserInterface.printFeedback("Invalid Input! Please enter a valid plate number. (ABC123 or ABC1234)");
            } else if (vehicleType.equals(VehicleType.MOTORCYCLE) && !isValidMotorcyclePlate(input)) {
                UserInterface.printFeedback("Invalid Input! Please adhere to LTO's Motorcycle/Legacy Formats:\nhttps://lto.gov.ph/wp-content/uploads/2026/04/MEMORANDUM-NO.-MVL-2026-117.pdf");
            } else {
                isValid = true;
            }
        }

        return input;
    }

    // For searching for existing plate numbers
    public static String findPlateNumber(String prompt, HashMap<String, Vehicle> vehicles) {
        boolean isValid = false;
        String input = "";

        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine().trim();

            if (vehicles.containsKey(input)) {
                isValid = true;
            } else {
                UserInterface.printFeedback("Invalid Input! Plate Number does not Exist.");
            }
        }

        return input;
    }

    public static String inputModel(String prompt) {
        boolean isValid = false;
        String input = "";
        
        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            
            if (input != null && !input.isEmpty() && !input.isBlank()) {
                isValid = true;
            } else {
                UserInterface.printFeedback("Invalid Input! Please enter a valid model.");
            }
        }

        return input;
    }

    public static double inputRatePerDay(String prompt) {
        boolean isValid = false;
        String input = "";
        
        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            
            if (input.matches("^(0|[1-9][0-9]*)(\\.[0-9]{1,2})?$")) {
                isValid = true;
            } else {
                UserInterface.printFeedback("Invalid Input! Please enter a valid rate per day.");
            }
        }

        return Double.parseDouble(input);
    }

    public static int inputDetails(String prompt, VehicleType vehicleType) {
        boolean isValid = false;
        String input = "";
        
        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            
            if (!input.matches("^[1-9][0-9]*$")) {
                UserInterface.printFeedback("Invalid Input! Please enter a valid positive integer.");
            } else if (vehicleType == VehicleType.CAR && !input.matches("^[1-8]$")) {
                UserInterface.printFeedback("Invalid Input! Please enter the number of seats ranging from 1 to 8.");
            } else if (vehicleType == VehicleType.VAN && Integer.parseInt(input) > 5000) {
                UserInterface.printFeedback("Invalid Input! Please enter a number below 5000");
            } else if (vehicleType == VehicleType.MOTORCYCLE && Integer.parseInt(input) > 2500) {
                UserInterface.printFeedback("Invalid Input! Please enter a number below 2500");
            } else {
                isValid = true;
            }
        }

        return Integer.parseInt(input);
    }

    public static int inputPositiveInteger(String prompt) {
        boolean isValid = false;
        String input = "";
        
        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            
            if (input.matches("^[1-9][0-9]*$")) {
                isValid = true;
            } else {
                UserInterface.printFeedback("Invalid Input! Please enter a valid positive integer.");
            }
        }

        return Integer.parseInt(input);
    }

    // Helper method to see if the motorcycle plate is valid based on LTO's Formats
    public static boolean isValidMotorcyclePlate(String input) {
        if (!input.matches("^[A-Z0-9]{6,7}$")) {
            return false;
        }

        long letterCount = input.chars().filter(Character::isLetter).count();
        long digitCount = input.chars().filter(Character::isDigit).count();

        boolean newFormat = (letterCount == 3 && digitCount == 3) || (letterCount == 2 && digitCount == 4);
        boolean legacyFormat = (input.length() == 7 && letterCount == 2 && digitCount == 5);

        return newFormat || legacyFormat;
    }

    // Helper method to close scanner when program ends in main
    protected static void closeScanner() {
        sc.close();
    }
}