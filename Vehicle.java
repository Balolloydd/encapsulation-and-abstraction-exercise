public abstract class Vehicle {
    private String plateNumber;
    private String model;
    private double ratePerDay;
    private boolean isAvailable; 

    public Vehicle(String plateNumber, String model, double ratePerDay) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.ratePerDay = ratePerDay;
        this.isAvailable = true;
    }

    public void setStatus(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getModel() {
        return model;
    }

    public double getRatePerDay() {
        return ratePerDay;
    }

    public boolean getStatus() {
        return isAvailable;
    }

    public abstract double rentalCost(int numberOfDays);
    public abstract String getVehicleType();
}