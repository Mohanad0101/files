package part5.part5_1;

public class ZooDemo {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();

        Lion lion = new Lion("Simba", 5, 180.0, 25);
        Elephant elephant = new Elephant("Dumbo", 12, 1200.0, 40);

        zoo.addAnimal(lion);
        zoo.addAnimal(elephant);

        zoo.makeNoise();
        lion.roar();
        elephant.trumpet();

        lion.train("сидеть");
        lion.train("голос");
        System.out.println("Команды льва: " + lion.listCommands());

        System.out.println("Голодные: " + zoo.getHungryAnimals().size());
        zoo.feedAll();
        System.out.println("Голодные после кормления: " + zoo.getHungryAnimals().size());

        System.out.println("Поиск Simba: " + zoo.findAnimal("Simba").isPresent());
    }
}
