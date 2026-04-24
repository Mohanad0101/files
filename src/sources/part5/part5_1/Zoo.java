package part5.part5_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Zoo {
    private final List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal a) {
        animals.add(a);
    }

    public void feedAll() {
        for (Animal a : animals) {
            a.eat(200);
        }
    }

    public void makeNoise() {
        for (Animal a : animals) {
            a.makeSound();
        }
    }

    public List<Animal> getHungryAnimals() {
        List<Animal> hungry = new ArrayList<>();
        for (Animal a : animals) {
            if (a.getEnergyLevel() < 30) hungry.add(a);
        }
        return hungry;
    }

    public Optional<Animal> findAnimal(String name) {
        for (Animal a : animals) {
            if (a.getName().equalsIgnoreCase(name)) return Optional.of(a);
        }
        return Optional.empty();
    }
}
