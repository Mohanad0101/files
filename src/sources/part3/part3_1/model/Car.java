package part3.part3_1.model;

public class Car extends Vehicle {
    private final int doors;
    private final boolean automatic;

    public Car(String brand, String model, int year, double fuelLevel, int doors, boolean automatic) {
        super(brand, model, year, fuelLevel);
        this.doors = doors;
        this.automatic = automatic;
    }

    public int getDoors() { return doors; }
    public boolean isAutomatic() { return automatic; }

    @Override
    public double getFuelConsumption() {
        return automatic ? 9.5 : 8.0;
    }

    @Override
    public String getType() {
        return "Car";
    }

    public void honk() {

        System.out.println(getBrand() + " " + getModel() + ": beep-beep!");
    }
}
