package part5.part5_1;

import java.util.ArrayList;
import java.util.List;

public class Lion extends Predator implements Trainable {
    private final List<String> commands = new ArrayList<>();

    public Lion(String name, int age, double weight, int energyLevel) {
        super(name, age, weight, energyLevel);
    }

    @Override
    public void makeSound() {
        System.out.println("Р-р-р!");
    }

    public void roar() {
        System.out.println(name + " громко рычит!");
    }

    @Override
    public void train(String command) {
        if (command != null && !command.isBlank()) {
            commands.add(command.trim());
        }
    }

    @Override
    public List<String> listCommands() {
        return new ArrayList<>(commands);
    }
}
