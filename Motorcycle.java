public class Motorcycle extends Vehicle {
    private int engineDisplacement;
    
    public Motorcycle(String plateNumber, String model, double ratePerDay, int engineDisplacement) {
        super(plateNumber, model, ratePerDay);
        this.engineDisplacement = engineDisplacement;
    }

    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    @Override
    public double rentalCost(int numberOfDays) {
        return getRatePerDay() * numberOfDays;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.MOTORCYCLE;
    }
}