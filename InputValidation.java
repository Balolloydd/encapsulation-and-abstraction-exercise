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

    // mustExist parameter is for seeing if the plate number should be existing already or not
    public static String inputPlateNumber(String prompt, boolean mustExist, HashMap<String, Vehicle> vehicles) {
        boolean isValid = false;
        String input = "";
        
        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            
            if (!input.matches("^[A-Z]{3}[0-9]{3,4}$")) {
                UserInterface.printFeedback("Invalid Input! Please enter a valid plate number. (ABC1234)");
                continue;
            }

            if (mustExist && !vehicles.containsKey(input)) {
                UserInterface.printFeedback("Invalid Input! Plate number does not exist.");
            } else if (!mustExist && vehicles.containsKey(input)) {
                UserInterface.printFeedback("Invalid Input! Plate number already exists."); 
            } else {
                isValid = true;
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
            
            if (input.matches("^[0-9][0-9]*(\\.[0-9]{1,2})?$")) {
                isValid = true;
            } else {
                UserInterface.printFeedback("Invalid Input! Please enter a valid rate per day.");
            }
        }

        return Double.parseDouble(input);
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

    // Helper method to close scanner when program ends in main
    protected static void closeScanner() {
        sc.close();
    }
}