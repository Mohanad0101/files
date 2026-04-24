package part4.part4_2;

import java.util.ArrayList;
import java.util.List;

public class ShapeTest {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle("красный", 5));
        shapes.add(new Rectangle("синий", 4, 6));
        shapes.add(new Square("зелёный", 5));
        shapes.add(new Circle("жёлтый", 3));

        System.out.println("=== Все фигуры ===");
        for (Shape s : shapes) {
            s.draw();
            System.out.println("  " + s);
        }

        System.out.println("\n=== Сортировка по площади ===");
        shapes.sort(Shape::compareArea);
        shapes.forEach(System.out::println);

        System.out.println("\n=== Только круги ===");
        for (Shape s : shapes) {
            if (s instanceof Circle c) {
                System.out.printf("Круг через toString: %s%n", c);
            }
        }

        double totalArea = shapes.stream().mapToDouble(Shape::area).sum();
        System.out.printf("%nОбщая площадь: %.2f%n", totalArea);
    }
}
