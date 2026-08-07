public class Van extends Vehicle {
    private int cargoCapacity;
    
    public Van(String plateNumber, String model, double ratePerDay, int cargoCapacity) {
        super(plateNumber, model, ratePerDay);
        this.cargoCapacity = cargoCapacity;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public double rentalCost(int numberOfDays) {
        return getRatePerDay() * numberOfDays + 500;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.VAN;
    }   
}