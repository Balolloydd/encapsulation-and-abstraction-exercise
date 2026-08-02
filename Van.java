public class Van extends Vehicle {
    private int cargoCapacity;
    
    public Van(String plateNumber, String model, double ratePerDay, int cargoCapacity) {
        super(plateNumber, model, ratePerDay);
        this.cargoCapacity = cargoCapacity;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }
}