package part3.part3_1.model;

public class Truck extends Vehicle {
    private final double cargoCapacityTons;

    public Truck(String brand, String model, int year, double fuelLevel, double cargoCapacityTons) {
        super(brand, model, year, fuelLevel);
        this.cargoCapacityTons = cargoCapacityTons;
    }

    @Override
    public double getFuelConsumption() {

        return 20.0 + cargoCapacityTons * 3.0;
    }

    @Override
    public String getType() {

        return "Truck";
    }
}
