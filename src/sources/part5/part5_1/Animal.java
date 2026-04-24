package part5.part5_1;

public abstract class Animal {
    protected String name;
    protected int age;
    protected double weight;
    protected int energyLevel;

    protected Animal(String name, int age, double weight, int energyLevel) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.energyLevel = Math.max(0, Math.min(100, energyLevel));
    }

    public void eat(int calories) {
        int boost = Math.max(1, calories / 20);
        energyLevel = Math.min(100, energyLevel + boost);
    }

    public void sleep(int hours) {
        int boost = Math.max(1, hours * 10);
        energyLevel = Math.min(100, energyLevel + boost);
    }

    public abstract void makeSound();

    public int getEnergyLevel() { return energyLevel; }
    public String getName() { return name; }
}
