package part5.part5_1;

public abstract class Predator extends Animal {
    protected Predator(String name, int age, double weight, int energyLevel) {
        super(name, age, weight, energyLevel);
    }

    public void hunt() {
        energyLevel = Math.min(100, energyLevel + 30);
        weight = Math.max(0.0, weight - 0.5);
    }
}
