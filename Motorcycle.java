public class Motorcycle extends Vehicle {
    private int engineDisplacement;
    
    public Motorcycle(String plateNumber, String model, double ratePerDay, int engineDisplacement) {
        super(plateNumber, model, ratePerDay);
        this.engineDisplacement = engineDisplacement;
    }

    @Override
    public double getRentalCost(int numberOfDays) {
        return getRatePerDay() * numberOfDays;
    }

    @Override
    public String getDetails() {
        return String.format("%d cc", engineDisplacement);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.MOTORCYCLE;
    }
}