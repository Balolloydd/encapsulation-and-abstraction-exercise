public class Car extends Vehicle {
    private int numSeats;
    
    public Car(String plateNumber, String model, double ratePerDay, int numSeats) {
        super(plateNumber, model, ratePerDay);
        this.numSeats = numSeats;
    }

    public int getNumSeats() {
        return numSeats;
    }

    @Override
    public double rentalCost(int numberOfDays) {
        return getRatePerDay() * numberOfDays;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }
}