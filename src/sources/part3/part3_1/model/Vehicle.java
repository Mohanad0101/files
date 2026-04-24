package part3.part3_1.model;

public abstract class Vehicle {
    private final String brand;
    private final String model;
    private final int year;
    private double fuelLevel;

    protected Vehicle(String brand, String model, int year, double fuelLevel) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        setFuelLevel(fuelLevel);
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getFuelLevel() { return fuelLevel; }

    public void setFuelLevel(double fuelLevel) {
        if (fuelLevel < 0.0 || fuelLevel > 1.0) {
            throw new IllegalArgumentException("fuelLevel должен быть в диапазоне 0.0..1.0");
        }
        this.fuelLevel = fuelLevel;
    }

    public abstract double getFuelConsumption();
    public abstract String getType();

    public double calculateFuelNeeded(double distanceKm) {

        return (distanceKm / 100.0) * getFuelConsumption();
    }

    public boolean canTravel(double distanceKm, double tankCapacityLiters) {
        double available = fuelLevel * tankCapacityLiters;
        return available >= calculateFuelNeeded(distanceKm);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d), fuel=%.0f%%",
            brand, model, year, fuelLevel * 100.0);
    }
}
