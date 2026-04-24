package part4.part4_2;

public class Square extends Rectangle {
    public Square(String color, double side) {
        super(color, side, side);
    }

    @Override
    public void draw() {
        System.out.printf("Рисую %s квадрат %.1f x %.1f%n", color, width, height);
    }
}
