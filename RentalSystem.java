public class RentalSystem {
    
    public static String getVehicleType() {
        int choice = InputValidation.inputChoice("Enter your choice: ", 1, 3);
        
        switch (choice) {
            case 1:
                return "Car";
            case 2:
                return "Van";
            case 3:
                return "Motorcycle";
            default:
                UserInterface.printFeedback("Invalid Input! Please try again.");
                return "";
        }
    }

    public static Vehicle addVehicle(String vehicleType) {
        String plateNumber = InputValidation.inputPlateNumber("Enter Plate Number: ");
        String model = InputValidation.inputModel("Enter Model: ");
        double ratePerDay = InputValidation.inputRatePerDay("Enter Base Rate Per Day: ");

        if (vehicleType.equalsIgnoreCase("Car")) {
            int numSeats = InputValidation.inputPositiveInteger("Enter Number of Seats: ");
            return new Car(plateNumber, model, ratePerDay, numSeats);
        } else if (vehicleType.equalsIgnoreCase("Van")) {
            int cargoCapacity = InputValidation.inputPositiveInteger("Enter Cargo Capacity: ");
            return new Van(plateNumber, model, ratePerDay, cargoCapacity);
        } else if (vehicleType.equalsIgnoreCase("Motorcycle")) {
            int engineDisplacement = InputValidation.inputPositiveInteger("Enter Engine Displacement: ");
            return new Motorcycle(plateNumber, model, ratePerDay, engineDisplacement);
        } else {
            UserInterface.printFeedback("Invalid Vehicle Type! Please try again.");
            return null;
        }
    }
}