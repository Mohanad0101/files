package part3.part3_1.model;

import part3.part3_1.interfaces.Electric;

public class ElectricCar extends Car implements Electric {
    private double batteryLevel;
    private final double maxRangeKm;

    public ElectricCar(
        String brand, String model, int year, double fuelLevel, int doors, boolean automatic,
        double batteryLevel, double maxRangeKm
    ) {
        super(brand, model, year, fuelLevel, doors, automatic);
        this.batteryLevel = Math.max(0.0, Math.min(1.0, batteryLevel));
        this.maxRangeKm = maxRangeKm;
    }

    @Override
    public double getBatteryLevel() {

        return batteryLevel;
    }

    @Override
    public double getRangeKm() {

        return maxRangeKm * batteryLevel;
    }

    @Override
    public void charge(double hours) {
        if (hours <= 0) return;
        batteryLevel = Math.min(1.0, batteryLevel + 0.2 * hours);
    }

    @Override
    public double getFuelConsumption() {
        return 0.0;
    }

    @Override
    public String getType() {
        return "ElectricCar";
    }
}
