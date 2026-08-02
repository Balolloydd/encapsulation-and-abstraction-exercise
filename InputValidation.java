import java.util.Scanner;

public class InputValidation {
    private static Scanner sc = new Scanner(System.in);

    public static int inputChoice(String prompt, int min, int max) {
        boolean isValid = false;
        String choice = "";
        
        while (!isValid) {
            System.out.print(prompt);
            choice = sc.nextLine().trim();
            
            if (choice.matches(String.format("%d-%d", min, max))) {
                isValid = true;
            } else {
                System.out.printf("Invalid Input! Please enter a number between %d and %d.\n", min, max);
            }
        }

        return Integer.parseInt(choice);
    }

    public static String inputPlateNumber(String prompt) {
        boolean isValid = true;
        String input = "";
        
        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            
            if (!input.matches("^[A-Z]{3}[0-9]{3,4}$")) {
                System.out.println("Invalid Input! Please enter a valid plate number.");
                continue;
            }

            for (Vehicle vehicle : Main.getVehiclesList()) {
                if (vehicle.getPlateNumber().equalsIgnoreCase(input)) {
                    System.out.println("Invalid Input! Plate number already exists.");
                    isValid = false;
                    break;
                }
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
                System.out.println("Invalid Input! Please enter a valid model.");
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
            
            if (input.matches("^[1-9][0-9]*(\\.[0-9]{1,2})?$")) {
                isValid = true;
            } else {
                System.out.println("Invalid Input! Please enter a valid rate per day.");
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
                System.out.println("Invalid Input! Please enter a valid positive integer.");
            }
        }

        return Integer.parseInt(input);
    }

    // Helper method to close scanner when program ends in main
    protected static void closeScanner() {
        sc.close();
    }
}