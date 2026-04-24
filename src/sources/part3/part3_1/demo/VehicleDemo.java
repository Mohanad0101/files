package part3.part3_1.demo;

import java.util.ArrayList;
import java.util.List;
import part3.part3_1.interfaces.Electric;
import part3.part3_1.model.Car;
import part3.part3_1.model.ElectricCar;
import part3.part3_1.model.Truck;
import part3.part3_1.model.Vehicle;

public class VehicleDemo {
    public static void main(String[] args) {
        List<Vehicle> fleet = new ArrayList<>();
        fleet.add(new Car("Toyota", "Camry", 2020, 0.6, 4, true));
        fleet.add(new Car("Lada", "Vesta", 2021, 0.5, 4, false));
        fleet.add(new Truck("Kamaz", "5490", 2019, 0.7, 12.0));
        fleet.add(new ElectricCar("Tesla", "Model 3", 2022, 0.0, 4, true, 0.8, 550));

        double distance = 500.0;
        for (Vehicle v : fleet) {
            System.out.printf("%s | %s | fuel for %.0f km: %.2f%n",
                v.getType(), v, distance, v.calculateFuelNeeded(distance));
            if (v instanceof Car car) {
                car.honk();
            }
            if (v instanceof Electric electric) {
                System.out.printf("  Электрозапас хода: %.1f км%n", electric.getRangeKm());
            }
        }
    }
}
