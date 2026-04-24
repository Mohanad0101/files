package part5.part5_1;

public class Elephant extends Herbivore {
    public Elephant(String name, int age, double weight, int energyLevel) {
        super(name, age, weight, energyLevel);
    }

    @Override
    public void makeSound() {
        System.out.println("Тууут!");
    }

    public void trumpet() {
        System.out.println(name + " трубит хоботом!");
    }
}
