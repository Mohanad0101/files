package part5.part5_1;

public abstract class Herbivore extends Animal {
    protected Herbivore(String name, int age, double weight, int energyLevel) {
        super(name, age, weight, energyLevel);
    }

    public void graze() {
        energyLevel = Math.min(100, energyLevel + 15);
    }
}
