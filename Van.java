public class Van extends Vehicle {
    private int cargoCapacity;
    
    public Van(String plateNumber, String model, double ratePerDay, int cargoCapacity) {
        super(plateNumber, model, ratePerDay);
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public double getRentalCost(int numberOfDays) {
        return getRatePerDay() * numberOfDays + 500;
    }

    @Override
    public String getDetails() {
        return String.format("%d kg", cargoCapacity);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.VAN;
    }   
}