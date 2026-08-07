public class Car extends Vehicle {
    private int numSeats;
    
    public Car(String plateNumber, String model, double ratePerDay, int numSeats) {
        super(plateNumber, model, ratePerDay);
        this.numSeats = numSeats;
    }

    @Override
    public double getRentalCost(int numberOfDays) {
        return getRatePerDay() * numberOfDays;
    }

    @Override
    public String getDetails() {
        return String.format("%d Seats", numSeats);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }
}